package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	private By logoutLink = By.linkText("Logout");
	private By accSecHeaders = By.cssSelector("div#content h2");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getAccPageTitle() {
		return eleUtil.waitForTitleIs(AppConstants.ACCOUNT_PAGE_TITLE, TimeUtil.Dafault_TIME_OUT);
	}

	public String getAccPageURL() {
		return eleUtil.waitForUrlContains(AppConstants.Account_PAGE_FRACTION_URL, TimeUtil.Dafault_TIME_OUT);
	}

	public boolean isSearchExist() {
		return eleUtil.waitForElementVisible(search, TimeUtil.Dafault_TIME_OUT).isDisplayed();
	}

	public boolean isLogoutExist() {
		return eleUtil.waitForElementVisible(search, TimeUtil.Dafault_TIME_OUT).isDisplayed();
	}

	public List<String> getAccountsPageSecHeaders() {
		List<WebElement> secheadersList = eleUtil.waitForElementsVisible(accSecHeaders, TimeUtil.Dafault_TIME_OUT);
		List<String> secHeadersvalList = new ArrayList<String>();
		for (WebElement e : secheadersList) {
			String Text = e.getText();
			secHeadersvalList.add(Text);
		}

		return secHeadersvalList;
	}

	public ResultsPage PerformSerach(String productName) {
		System.out.println("Product Search for : "+productName);

		if (isSearchExist()) {
			eleUtil.doSendKeys(search, productName);
			eleUtil.doClick(searchIcon);
			return new ResultsPage(driver);
		}
		return null;
	}

}
