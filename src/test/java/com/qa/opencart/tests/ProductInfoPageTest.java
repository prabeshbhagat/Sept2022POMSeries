package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void productInfoSetup() {
		accountsPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		//accountsPage = loginPage.doLogin("prabeshkbk@gmail.com", "Selenium");
	}

	@DataProvider
	public Object[][] getProductTestData() {

		return new Object[][] { { "Macbook", "MacBook Air" }, { "Macbook", "MacBook Pro" },
				{ "Samsung", "Samsung SyncMaster 941BW" }, { "imac", "iMac" }, { "apple", "Apple Cinema 30\"" } };

	}

	@Test(dataProvider = "getProductTestData")
	public void productHeaderTest(String serachKey, String mainProductName) {
		resultsPage = accountsPage.PerformSerach(serachKey);
		productInfoPage = resultsPage.selectProduct(mainProductName);
		String actHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(actHeader, mainProductName);
	}

	@DataProvider
	public Object[][] getProductImagesTestData() {

		return new Object[][] { { "Macbook", "MacBook Air", 4 }, { "Macbook", "MacBook Pro", 4 },
				{ "Samsung", "Samsung Galaxy Tab 10.1", 7 }, { "imac", "iMac", 3 },
				{ "apple", "Apple Cinema 30\"", 6 } };

	}

	@Test(dataProvider = "getProductImagesTestData")
	public void productImagesCountTest(String searchKey, String mainProductName, int imageCount) {
		resultsPage = accountsPage.PerformSerach(searchKey);
		productInfoPage = resultsPage.selectProduct(mainProductName);
		int actImagesCount = productInfoPage.getProductImagesCount();
		System.out.println("The actImagesCount  is :" + actImagesCount);
		Assert.assertEquals(actImagesCount, imageCount);
	}

	@Test
	public void productMetaDataTest() {
		resultsPage = accountsPage.PerformSerach("Macbook");
		productInfoPage = resultsPage.selectProduct("MacBook Air");
		Map<String, String> actProductInfo = productInfoPage.getProductInformation();

		softassert.assertEquals(actProductInfo.get("Brand"), "Apple");
		softassert.assertEquals(actProductInfo.get("Availability"), "In Stock");
		softassert.assertEquals(actProductInfo.get("actualExTaxprice"), "Ex Tax: $1,000.00");
		softassert.assertEquals(actProductInfo.get("Reward Points"), "700");
		softassert.assertAll();

	}

}
