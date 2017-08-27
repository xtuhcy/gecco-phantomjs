package com.geccocrawler.gecco.demo.xsl;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.DeriveSchedulerContext;
import com.geccocrawler.gecco.spider.HtmlBean;

@PipelineName("xingShuLin")
@Gecco(matchUrl="http://www.xingshulin.com/yxzl/type/9337", pipelines="consolePipeline", downloader="phantomJSDownloader")
public class XingShuLin implements HtmlBean , Pipeline<XingShuLin> {
	
	private static final long serialVersionUID = 8277485934932296997L;
	
	@Request
	private HttpRequest request;
	
	@HtmlField(cssPath="#root")
	private String body;
	
	public void process(XingShuLin bean) {
		HttpRequest sub = bean.getRequest().subRequest("https://epocket.xingshulin.com/medicalCase/getMedicalCaseCategoryList?sessionKey=7B34653864333265383162313634386434623730393266353431336162343261614A39775155573861333273502A6579476C4432513035305632756F6A526232497D2C7B35334531433136344343353733464643363237463536383733463030383531377D2C7B747275657D2C7B3230307D2C7B313337373432367D2C7B64656136373564623561306534326333393130666164666133366633383238663570387547425232717A754D386A4F6C6A6F504B3346507569556B70592A3D647D2C7B66383839633865346630353263323339353537626638326632376161643363387D2C7B323031362D31322D32382031363A34393A33357D2C7B312E302E307D2C7B7777777D2C7B7777775F312E302E302D4F53585F31305F31325F317D");
		DeriveSchedulerContext.into(sub);
	}
	
	public HttpRequest getRequest() {
		return request;
	}

	public void setRequest(HttpRequest request) {
		this.request = request;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	public static void main(String[] args) {
		HttpRequest request = new HttpGetRequest("http://www.xingshulin.com/yxzl/type/9337");

		request.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		request.addHeader("Accept-Encoding", "gzip, deflate, sdch");
		request.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
		request.addHeader("Cache-Control", "no-cache");
		request.addHeader("Connection", "keep-alive");
		request.addHeader("Host", "www.xingshulin.com");
		request.addHeader("Pragma", "no-cache");
		request.addHeader("Upgrade-Insecure-Requests", "1");
		request.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
		
		GeccoEngine.create()
		.classpath("com.geccocrawler.gecco.demo.xsl")
		.start(request)
		.start();
	}

}
