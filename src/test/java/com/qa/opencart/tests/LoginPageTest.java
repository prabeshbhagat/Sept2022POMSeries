package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.AppErrors;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic :9024 Design Login page for open cart shopping application")
@Story("User Story:101 Create loginpage funcationality for open cart login page")
public class LoginPageTest extends BaseTest {

	
	@Description("Login page title test")
	@Severity(SeverityLevel.TRIVIAL)
	@Test
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		System.out.println("The title of LoginPage is :" + actTitle);
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE,AppErrors.NO_TITLE_MATCHED);
	}

	@Description("Login page URL test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void loginPageURLTest() {
		String actURL = loginPage.getLoginPageURL();
		System.out.println("The URL of LoginPage is :" + actURL);
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL),AppErrors.NO_URL_MATCHED);
	}
	
	@Description("forgot pwd link in login page")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void forgotPwdLinkExist() {
		Assert.assertTrue(loginPage.forgottenPwdLinkExist());
	}
	
	@Description("User is able to Login on Login page Test")
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void loginTest() {
		accountsPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accountsPage.isLogoutExist(),AppErrors.LOGIN_UNSUCESSFUL);
	}

}
