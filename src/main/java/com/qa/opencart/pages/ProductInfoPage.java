package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By productHeader = By.cssSelector("div#content h1");
	private By productImage = By.cssSelector("a.thumbnail");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]//li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]//li");

	private Map<String, String> productMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getProductHeader() {
		return eleUtil.doGetElementText(productHeader);
	}

	public int getProductImagesCount() {
		return eleUtil.waitForElementsVisible(productImage, TimeUtil.Dafault_TIME_OUT).size();
	}

	public Map<String, String> getProductInformation() {
		//productMap = new HashMap<String, String>();
		productMap = new TreeMap<String, String>();
		//productMap = new LinkedHashMap<String, String>();
		getProductMetaData();
		getProductPriceData();
		System.out.println(productMap);
		return productMap;
	}

	private void getProductMetaData() {
		List<WebElement> metaDataList = eleUtil.getElements(productMetaData);
		System.out.println("The Product Metadata Count is :" + metaDataList.size());

		for (WebElement e : metaDataList) {
			//System.out.println(e.getText());
			String meta = e.getText();
			String[] metaData = meta.split(":");
			String metaKey = metaData[0].trim();
			String metaValue = metaData[1].trim();
			productMap.put(metaKey, metaValue);
		}
	}

	private void getProductPriceData() {
		List<WebElement> mataPriceList = eleUtil.getElements(productPriceData);
		System.out.println("The  Price Count is :" + mataPriceList.size());
		String price = mataPriceList.get(0).getText().trim();
		String ExTaxprice = mataPriceList.get(1).getText().trim();

		productMap.put("actualPrice", price);
		productMap.put("actualExTaxprice", ExTaxprice);
	}

}
