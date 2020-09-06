package com.syntax.class2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssertionExample {

	public static WebDriver driver;

	@BeforeMethod
	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://166.62.36.207/humanresources/symfony/web/index.php/auth/login");
	}

	@AfterMethod
	public void logout() {
		driver.quit();
	}

	@Test
	public void invalidLoginError() throws InterruptedException {
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("acbkajsbcxjk");
		driver.findElement(By.id("btnLogin")).click();

		SoftAssert softAssertion = new SoftAssert();
		String expectedErrorMessage1 = "Invalid credential";
		WebElement errorMessage = driver.findElement(By.id("spanMessage"));
		// 1 validation
		softAssertion.assertEquals(errorMessage.getText(), expectedErrorMessage1);

		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("");
		driver.findElement(By.id("btnLogin")).click();

		String expectedErrorMessage2 = "Password cannot be";
		errorMessage = driver.findElement(By.id("spanMessage"));
		// 2 validation
		softAssertion.assertEquals(errorMessage.getText(), expectedErrorMessage2);
		Thread.sleep(3000);

		System.out.println("I am a text after the assertion");
		System.out.println(" --- This is the end of the test ---- ");

		softAssertion.assertAll(); // to throw all failed assertion
	}

}
