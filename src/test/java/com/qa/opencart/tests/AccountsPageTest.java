package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.AppErrors;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}

	@Test
	public void accPageTitleTest() {
		String actTitle = accountsPage.getAccPageTitle();
		System.out.println("Account page title is :" + actTitle);
		Assert.assertEquals(actTitle, AppConstants.ACCOUNT_PAGE_TITLE);
	}

	@Test
	public void accPageURLTest() {
		String actURL = accountsPage.getAccPageURL();
		System.out.println("The title of AccPage is :" + actURL);
		Assert.assertTrue(actURL.contains(AppConstants.Account_PAGE_FRACTION_URL), AppErrors.NO_URL_MATCHED);
	}

	@Test
	public void LogoutExistTest() {
		Assert.assertTrue(accountsPage.isLogoutExist());
	}

	@Test
	public void SearchExistTest() {
		Assert.assertTrue(accountsPage.isSearchExist());
	}

	@Test
	public void accPageHeadersTest() {
		List<String> acHeaderList = accountsPage.getAccountsPageSecHeaders();
		Assert.assertEquals(acHeaderList, AppConstants.EXPECTED_ACC_HEADERS_LIST);
	}

	@DataProvider
	public Object getProductName() {
		return new Object[][] { { "iMac" }, { "Samsung" }, { "Macbook" } };
	}

	// TDD
	@Test(dataProvider = "getProductName")
	public void productSearchTest(String productName) {
		resultsPage = accountsPage.PerformSerach(productName);
		String actTitle = resultsPage.getSearchPageTitle(productName);
		System.out.println("Search Page Title is :" + actTitle);
		softassert.assertEquals(actTitle, AppConstants.Search_PAGE_TITLE + " " + productName);
		Assert.assertTrue(resultsPage.getSearchProductsCount() >= 0);

	}

}
