package com.geccocrawler.gecco.demo;

import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.response.HttpResponse;

public class PhantomJSTest {
	public static void main(String[] args) throws Exception {
		HttpResponse response = new PhantomJSDownloader("D:\\phantomjs-2.1.1-windows", true).download(new HttpGetRequest("http://ip.zdaye.com/"), 0);
		System.out.println(response.getContent());
	}
}
