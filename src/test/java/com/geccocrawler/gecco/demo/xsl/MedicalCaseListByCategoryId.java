package com.geccocrawler.gecco.demo.xsl;

import com.alibaba.fastjson.JSONObject;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.JSONPath;
import com.geccocrawler.gecco.spider.JsonBean;

@Gecco(matchUrl="https://epocket.xingshulin.com/medicalCase/getMedicalCaseListByCategoryId?sessionKey={sessionKey}", pipelines="consolePipeline")
public class MedicalCaseListByCategoryId implements JsonBean {

	private static final long serialVersionUID = 8187810477694705722L;

	@JSONPath("$")
	private JSONObject jObject;

	public JSONObject getjObject() {
		return jObject;
	}

	public void setjObject(JSONObject jObject) {
		this.jObject = jObject;
	}

}
