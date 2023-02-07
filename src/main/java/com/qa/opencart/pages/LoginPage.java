package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1.Private By Locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By lgnBtn = By.xpath("//input[@value='Login']");
	private By forgottenPwd = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");

	// 2.Constructors:
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 3.Page Actions

	@Step("Get login page Title")
	public String getLoginPageTitle() {
		// return driver.getTitle();
		return eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.Dafault_TIME_OUT);
	}

	@Step("Get Login page URL")
	public String getLoginPageURL() {
		return eleUtil.waitForUrlContains(AppConstants.LOGIN_PAGE_FRACTION_URL, TimeUtil.Dafault_TIME_OUT);
	}

	@Step("Verify forgot pwd link")
	public Boolean forgottenPwdLinkExist() {
		return eleUtil.doIsDisplayed(forgottenPwd);
	}

	@Step("Login with USername : {0} and Passoword :{1}")
	public AccountsPage doLogin(String un, String pwd) {
		System.out.println("Creds are " + un + " : " + pwd);
		eleUtil.waitForElementVisible(emailId, TimeUtil.Dafault_TIME_OUT).sendKeys(un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(lgnBtn);

		return new AccountsPage(driver);
	}
	
	@Step("naviagting to register page")
	public RegPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegPage(driver);
	}

}
