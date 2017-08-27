package com.geccocrawler.gecco.downloader;

import org.openqa.selenium.phantomjs.PhantomJSDriver;

import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.response.HttpResponse;

@com.geccocrawler.gecco.annotation.Downloader("phantomJSDownloader")
public class PhantomJSDownloader implements Downloader {

	private PhantomJSDriver webDriver;
	
	public PhantomJSDownloader() {
		webDriver = new PhantomJSDriver();
	}
	
	@Override
	public HttpResponse download(HttpRequest request, int timeout) throws DownloadException {
		webDriver.get("http://ip.zdaye.com/");
		return null;
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}

}
