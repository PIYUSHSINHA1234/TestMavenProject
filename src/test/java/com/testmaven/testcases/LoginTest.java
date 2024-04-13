package com.testmaven.testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.testmaven.base.Base;
import com.testmaven.utilities.DataUtils;

public class LoginTest extends Base {

	@Test(dataProviderClass = DataUtils.class, dataProvider = "dp")
	public void dologin(String username, String password) throws Throwable {
		type("username_XPATH", username);
		waits("implicit.wait");
		type("password_ID", password);
		copy("password_ID");
		clear("password_ID");
		// unclickable("btn_XPATH");
		Thread.sleep(10000);

		/*
		 * @Test()
		 * 
		 * public void selectdropdown() { select("dropdown_XPATH", 2); }
		 * 
		 * 
		 * }
		 */
	}
}