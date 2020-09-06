package com.syntax.class3;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HW {

	public static WebDriver driver;

	@BeforeMethod(alwaysRun = true)
	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://166.62.36.207/humanresources/symfony/web/index.php/auth/login");
		// driver.manage().window().maximize();
	}

	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {
		driver.quit();
	}

	@Test(dataProvider = "getData")
	public void multipleLogin(String username, String password, String name, String lastName)
			throws InterruptedException {
		driver.findElement(By.id("txtUsername")).sendKeys(username);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("input#btnLogin")).click();
		
		String welcomeText = driver.findElement(By.id("welcome")).getText();
		Assert.assertEquals(welcomeText, "Welcome " + name);

		TakesScreenshot ts = (TakesScreenshot) driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(sourceFile, new File("screenshots/HRMS/"+name+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@DataProvider
	public String[][] getData() {
		String[][] data = { { "skw123", ")(@!SkwSam@#$22", "Sam", "Wol" },
							{ "tamtw", "SyntaxHrm123%6((", "Tamara", "Watson" }, 
							{ "hjnna", "HjA<>123$", "Hana", "Anna" },
							{ "jbond", "BoJa1!2@3#4$5%6^", "James", "Bond" } };
		return data;
	}

}
