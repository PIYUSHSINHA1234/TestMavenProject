package com.testmaven.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
		public static ExtentReports extent;
	
	public static ExtentReports capturereports() {
		
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("C:\\Users\\HP\\eclipse-workspace\\TestMavenProject\\test-output\\report.html");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("DataDrivenReports");
		
		extent.attachReporter(spark);
		
		return extent;
	}
}
