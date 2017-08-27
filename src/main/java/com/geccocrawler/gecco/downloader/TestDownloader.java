package com.geccocrawler.gecco.downloader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.ErrorHandler;

import com.google.common.io.Files;

/**
 * cookie
 * post 暂时找不到方法支持
 * header
 * timeout
 * proxy 不知道如何动态替换代理服务器
 * 
 * @author huchengyi
 *
 */
public class TestDownloader {

	public static void main(String[] args) {
        DesiredCapabilities dcaps = DesiredCapabilities.phantomjs();
        dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX+"userAgent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
        dcaps.setCapability("acceptSslCerts", true);
        dcaps.setJavascriptEnabled(true);
        dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "D:\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
        dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_CUSTOMHEADERS_PREFIX+"test", "test");
        
        Proxy proxy = new Proxy();
        proxy.setProxyType(ProxyType.MANUAL);
        proxy.setAutodetect(false);
        String proxyStr = "127.0.0.1:8087";
        proxy.setHttpProxy(proxyStr);
        //dcaps.setCapability(CapabilityType.PROXY, proxy);
        
        PhantomJSDriver webDriver = new PhantomJSDriver(dcaps);
        //proxy = (Proxy)webDriver.getCapabilities().getCapability(CapabilityType.PROXY);
        //proxy.setHttpProxy("127.0.0.1:8081");
        System.out.println(webDriver.getErrorHandler().isIncludeServerErrors());
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        try {
        	webDriver.get("http://www.baidu.com123/");
			Files.write(webDriver.getPageSource(), new File("zdaye.html"), Charset.forName("UTF-8"));
		} catch (Exception e) {
			System.out.println("-------------------------------");
			e.printStackTrace();
		}
		webDriver.quit();
	}

}
