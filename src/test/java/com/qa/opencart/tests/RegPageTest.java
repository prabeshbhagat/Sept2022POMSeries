package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegPageTest extends BaseTest {

	@BeforeClass
	public void regPageSetUp() {
		regPage = loginPage.navigateToRegisterPage();
	}

	public String getRandomNumber() {
		Random random = new Random();
		return "septAuto" + random.nextInt(5000) + "@gmail.com";
	}

	@DataProvider
	public Object[][] getRegTestData() {
		Object regData[][] = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return regData;
	}

	@Test(dataProvider = "getRegTestData")
	public void registerUserTest(String firstName, String lastNaame, String telephone, String pwd, String subscribe) {
		boolean flag = regPage.registerUser(firstName, lastNaame, getRandomNumber(), telephone, telephone, subscribe);
		Assert.assertTrue(flag);
	}

}
