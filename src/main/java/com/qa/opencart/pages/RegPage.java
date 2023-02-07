package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class RegPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By firstname = By.id("input-firstname");
	private By lastname = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephohone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By conPassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("//label[@class='radio-inline']/input[@value='1']");
	private By subscribeNo = By.xpath("//label[@class='radio-inline']/input[@value='0']");
	private By agreecheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@value='Continue']");

	private By registerSuccessMsg = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public RegPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public boolean registerUser(String firstName, String lastName, String email, String telephone, String password,
			String subscribe) {
		eleUtil.waitForElementVisible(this.firstname, TimeUtil.Dafault_TIME_OUT).sendKeys(firstName);
		eleUtil.doSendKeys(this.lastname, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephohone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.conPassword, password);

		if (subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
			;
		} else {
			eleUtil.doClick(subscribeNo);
		}

		eleUtil.doClick(agreecheckBox);
		eleUtil.doClick(continueButton);

		String successMsg = eleUtil.waitForElementVisible(registerSuccessMsg, TimeUtil.Dafault_TIME_OUT).getText();
		System.out.println(successMsg);
		if (successMsg.contains(AppConstants.REGISTER_SUCCESS)) {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		} else {
			eleUtil.doClick(registerLink);
		}
		return false;

	}

}
