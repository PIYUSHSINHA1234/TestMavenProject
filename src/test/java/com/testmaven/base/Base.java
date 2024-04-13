package com.testmaven.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


import com.testmaven.utilities.TestScreenshot;
import com.testmaven.utilities.XLUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	public static WebDriver driver;
	public static Logger log = Logger.getLogger(Base.class);
	public Properties config = new Properties();
	public Properties OR = new Properties();
	public static FileInputStream fi;
	public String browser = null;
	public XLUtils excel = new XLUtils(
			"C:\\Users\\HP\\eclipse-workspace\\TestMavenProject\\src\\test\\resources\\excel\\testdata.xlsx");

	@BeforeSuite
	public void setup() {
		PropertyConfigurator
				.configure(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\log4j.properties");

		try {
			fi = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			config.load(fi);
			log.info("Configure Properties file loaded successfully");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			fi = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			OR.load(fi);
			log.info("OR properties file loaded");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (config.getProperty("browser").equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.get(config.getProperty("testsiteURL"));
			log.info("--ChromeDriver Launched---");

		} else {
			System.out.println("Browser has not initialised");
			log.info("--chromedriver has not launched--");
		}
		try {
			TestScreenshot.capturescreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ExtentManager.capturereports();

		driver.get(config.getProperty("testsiteURL"));
	}

	public void type(String locator, String value) {
		if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}
	}

	public void click(String locator) {
		if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(locator)).click();
		}
	}

	public void waits(String locator) {
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty(locator)), TimeUnit.SECONDS);
	}

	public void unclickable(String locator) {
		if (locator.endsWith("_XPATH")) {
			WebElement element = driver.findElement(By.xpath(OR.getProperty(locator)));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
		} else if (locator.endsWith("_ID")) {
			WebElement element = driver.findElement(By.id(OR.getProperty(locator)));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
		}
	}

	public void select(String locator,int value) {
		if (locator.endsWith("_XPATH")) {
			WebElement element = driver.findElement(By.xpath(OR.getProperty(locator)));
			Select s = new Select(element);
			
             s.selectByIndex(value);
			}

		}
	public void copy(String locator)
	{
		if (locator.endsWith("_XPATH")) {
			WebElement element = driver.findElement(By.xpath(OR.getProperty(locator)));
			Actions action = new Actions(driver);
			action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
		} else if (locator.endsWith("_ID")) {
			WebElement element = driver.findElement(By.id(OR.getProperty(locator)));
			Actions action = new Actions(driver);
			action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
		}
	}


		
		public void clear(String locator)
		{
			if (locator.endsWith("_XPATH")) {
				WebElement element = driver.findElement(By.xpath(OR.getProperty(locator)));
				Actions action = new Actions(driver);
				action.sendKeys(Keys.BACK_SPACE).perform();
				
			} else if (locator.endsWith("_ID")) {
				WebElement element = driver.findElement(By.id(OR.getProperty(locator)));
				Actions action = new Actions(driver);
				action.sendKeys(Keys.BACK_SPACE).perform();
			}
		
	}
	
	@AfterSuite
	public void teardown() {
		driver.quit();
		log.info("All browser has been closed successfully");
	}
}
