package com.testmaven.listeners;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.testmaven.base.Base;
import com.testmaven.utilities.TestScreenshot;

public class Listeners extends TestListenerAdapter {

	public ExtentSparkReporter htmlreporter;
	public ExtentReports extent;
	public ExtentTest test;

	public void onStart(ITestContext testContext) {
		/*
		 * try { htmlreporter.loadXMLConfig(
		 * "C:/users/HP/eclipse-workspace/TestMavenProject/spark-config.xml"); } catch
		 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
		Date d = new Date();
		String filename = d.toString().replace(":", "_").replace(" ", "_") + ".html";
		htmlreporter = new ExtentSparkReporter(
				"C:\\Users\\HP\\eclipse-workspace\\TestMavenProject\\src\\test\\resources\\reports\\" + filename);
		htmlreporter.config().setDocumentTitle("TestMavenProject");
		htmlreporter.config().setReportName("AutomationTestExecutionReport");
		htmlreporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(htmlreporter);
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Host Name", "localhost");
		extent.setSystemInfo("user", "Piyush");

	}

	public void onTestStart(ITestResult tr) {

	}

	public void onTestSuccess(ITestResult tr) {
		test = extent.createTest(tr.getName());// create a new entry in the report
		test.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
		try {
			TestScreenshot.capturescreenshot();

			test.pass("Screenshot is below :" + test.addScreenCaptureFromPath(
					"C:\\Users\\HP\\eclipse-workspace\\TestMavenProject\\src\\test\\resources\\screenshot\\image.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestFailure(ITestResult tr) {
		test = extent.createTest(tr.getName());// create a new entry in the report
		test.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		try {
			TestScreenshot.capturescreenshot();

			test.fail("Screenshot is below :" + test.addScreenCaptureFromPath(
					"C:\\Users\\HP\\eclipse-workspace\\TestMavenProject\\src\\test\\resources\\screenshot\\image.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onFinish(ITestContext testContext) {
		if (extent != null) {
			extent.flush();
		}

	}

}
