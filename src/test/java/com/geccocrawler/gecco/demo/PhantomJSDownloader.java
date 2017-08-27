package com.geccocrawler.gecco.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpHost;

import com.geccocrawler.gecco.downloader.DownloadException;
import com.geccocrawler.gecco.downloader.Downloader;
import com.geccocrawler.gecco.downloader.proxy.Proxys;
import com.geccocrawler.gecco.downloader.proxy.ProxysContext;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.response.HttpResponse;

/**
 * 利用phantomJS充当下载器，可以解析动态页面
 * 
 * @author huchengyi
 *
 */

public class PhantomJSDownloader implements Downloader {
	
	private static Log log = LogFactory.getLog(PhantomJSDownloader.class);
	
	private String phantomjsHome;
	
	private boolean loadImages;
	
	public PhantomJSDownloader() {
		this("", false);
	}
	
	public PhantomJSDownloader(String phantomjsHome) {
		this(phantomjsHome, false);
	}
	
	public PhantomJSDownloader(String phantomjsHome, boolean loadImages) {
		this.phantomjsHome = phantomjsHome;
		this.loadImages = loadImages;
	}
	
	public HttpResponse download(HttpRequest request, int timeout) throws DownloadException {
		try {
			
			Runtime rt = Runtime.getRuntime();
			
			String cmd = buildCmd(request.getUrl());
			 
			System.out.println(cmd.toString());
			log.debug(cmd.toString());
			Process p = rt.exec(cmd.toString());
			
			InputStream is = p.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			StringBuffer sbf = new StringBuffer();
			String tmp = "";
			while ((tmp = br.readLine()) != null) {
				sbf.append(tmp);
			}
			
			String content = sbf.toString();
			HttpResponse response = new HttpResponse();
			response.setStatus(200);
			if(content.equalsIgnoreCase("fail")) {
				response.setStatus(500);
			}
			response.setContent(content);
			return response;
		} catch(IOException ex) {
			ex.printStackTrace();
			throw new DownloadException(ex.getMessage());
		}
	}

	private String buildCmd(String url) {
		URL jsUrl = getClass().getResource("/webpage.js");
		File file = new File(jsUrl.getPath());
		
		StringBuffer cmd = new StringBuffer();
		if(StringUtils.isNotEmpty(phantomjsHome)) {
			cmd.append(phantomjsHome).append(File.separator).append("bin").append(File.separator);
		}
		if(isWindows()) {
			cmd.append("phantomjs.exe");
		} else {
			cmd.append("phantomjs");
		}
		cmd.append(" ").append(file.getAbsolutePath());
		cmd.append(" ").append(url);
		cmd.append(" --load-images=").append(loadImages);
		
		//--proxy
		//--proxy-type=[http|socks5|none]
		HttpHost proxy = null;
		Proxys proxys = ProxysContext.get();
		boolean isProxy = ProxysContext.isEnableProxy();
		if(proxys != null && isProxy) {
			proxy = proxys.getProxy();
			if(proxy != null) {
				log.debug("proxy:" + proxy.getHostName()+":"+proxy.getPort());
				cmd.append(" --proxy=").append(proxy.getHostName()+":"+proxy.getPort());
				cmd.append(" --proxy-type=http");
			}
		}
		return cmd.toString();
	}
	
	private boolean isWindows() {
		String osName = System.getProperty("os.name");
		if (osName.startsWith("Windows")) {
            return true;
        }
		return false;
	}
	
	public void shutdown() {

	}

}
