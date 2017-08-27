package com.geccocrawler.gecco.demo.xsl;

import java.util.List;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.JSONPath;
import com.geccocrawler.gecco.spider.JsonBean;

@Gecco(matchUrl="https://epocket.xingshulin.com/medicalCase/getMedicalCaseCategoryList?sessionKey={sessionKey}", pipelines="consolePipeline")
public class MedicalCaseCategoryList implements JsonBean {

	private static final long serialVersionUID = -3055506275536938079L;

	@JSONPath("$.categoryVoList")
	private List<CategoryVo> categoryVoList;
	
	public List<CategoryVo> getCategoryVoList() {
		return categoryVoList;
	}

	public void setCategoryVoList(List<CategoryVo> categoryVoList) {
		this.categoryVoList = categoryVoList;
	}

	public static class CategoryVo implements JsonBean {

		private static final long serialVersionUID = -6042608499700659859L;
		
		@JSONPath("$.categoryImage")
		private String categoryImage;
		
		@JSONPath("$.name")
		private String name;
		
		@JSONPath("$.id")
		private int id;

		public String getCategoryImage() {
			return categoryImage;
		}

		public void setCategoryImage(String categoryImage) {
			this.categoryImage = categoryImage;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		
	}
}
