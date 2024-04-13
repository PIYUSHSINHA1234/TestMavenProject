package com.testmaven.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import com.testmaven.base.Base;

public class DataUtils extends Base {

	@DataProvider(name = "dp")
	public Object[][] getData() throws IOException {
		int rows = excel.getrowcount("LoginTest");
		System.out.println(rows);
		int cells = excel.getcellcount("LoginTest", rows);
		System.out.println(cells);

		Object[][] data = new Object[rows][cells];
		for (int i = 1; i <= rows; i++) {
			for (int j = 0; j < cells; j++) {
				data[i - 1][j] = excel.getCellData("LoginTest", i, j);
			}
		}

		return data;

	}

}
