package com.syntax.class2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Task {

	public static WebDriver driver;

	@BeforeMethod
	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://166.62.36.207/humanresources/symfony/web/index.php/auth/login");

	}

	@Test
	public void AddEmployeeAndVerify() throws InterruptedException {
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm123");
		driver.findElement(By.id("btnLogin")).click();
		driver.findElement(By.id("menu_pim_viewPimModule")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("menu_pim_addEmployee")).click();

		WebElement fullNameFeild = driver.findElement(By.xpath("//label[@class = 'hasTopFieldHelp']"));
		Assert.assertTrue(fullNameFeild.isDisplayed());

		WebElement empIDFeild = driver.findElement(By.xpath("//label[@for= 'employeeId']"));
		Assert.assertTrue(empIDFeild.isDisplayed());
		
		WebElement photograph = driver.findElement(By.xpath("//label[@for= 'photofile']"));
		Assert.assertTrue(photograph.isDisplayed());
		
		driver.findElement(By.id("firstName")).sendKeys("Ummar");
		driver.findElement(By.id("lastName")).sendKeys("Zahid");
		driver.findElement(By.xpath("//input[@class= 'duplexBox']")).sendKeys("C:\\Users\\ammar\\Desktop\\11.jpg");
		driver.findElement(By.id("btnSave")).click();
		
		
		

	}

//	@AfterMethod
//	public void quitBrowser() {
//		driver.quit();
//	}
}
