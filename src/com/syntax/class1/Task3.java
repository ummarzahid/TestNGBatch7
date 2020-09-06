package com.syntax.class1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Task3 {

	public static WebDriver driver;

	@BeforeMethod
	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://166.62.36.207/humanresources/symfony/web/index.php/auth/login");

	}

	@Test // TC 1
	public void validLogin() {
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm123");
		driver.findElement(By.id("btnLogin")).click();
		WebElement logo = driver.findElement(By.xpath("//img[@alt = 'OrangeHRM']"));
		boolean HrmLogo = logo.isDisplayed();
		System.out.println("Logo is displayed: " + HrmLogo);
	}

	@Test // TC 2
	public void invalidLogin() {
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");

		driver.findElement(By.id("btnLogin")).click();
		WebElement spanMesseage = driver.findElement(By.id("spanMessage"));
		boolean msgText = spanMesseage.isDisplayed();
		System.out.println("Password cannot be empty dispalyed: " + msgText);
	}

	@AfterMethod
	public void quitBrowser() {
		driver.quit();
	}

}
