package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class ResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private By searchResult = By.cssSelector("div.product-layout");

	public ResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 14;30
	public String getSearchPageTitle(String productName) {
		return eleUtil.waitForTitleContains(productName, TimeUtil.Dafault_TIME_OUT);
	}

	public int getSearchProductsCount() {
		int productCount= eleUtil.waitForElementsVisible(searchResult, TimeUtil.Dafault_TIME_OUT).size();
		System.out.println("Product results count :"+productCount);
		return productCount;
	}
	
	public ProductInfoPage selectProduct(String mainProductName) {
		System.out.println("The main product name :"+mainProductName);
		eleUtil.doClick(By.linkText(mainProductName));
		return new ProductInfoPage(driver);
	}

}
