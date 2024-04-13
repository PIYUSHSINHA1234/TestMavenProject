package com.testmaven.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.testmaven.base.Base;

public class TestScreenshot extends Base {
	
public static void capturescreenshot() throws IOException {	
File screenshot =  ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(screenshot, new File("C:\\Users\\HP\\eclipse-workspace\\TestMavenProject\\src\\test\\resources\\screenshot\\image.png"));

}
}
