package com.syntax.class4;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HwRecap {

	public static WebDriver driver;

	@BeforeMethod(alwaysRun = true)
	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://166.62.36.207/humanresources/symfony/web/index.php/auth/login");
		// driver.manage().window().maximize();
	}

	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {
		driver.quit();
	}

	@Test(dataProvider = "getData")
	public void addEmployee(String firstName, String lastName, String userName, String password, String screenshot)
			throws InterruptedException {
		driver.findElement(By.xpath("(//span[@class='form-hint']/preceding-sibling::input)[1]")).sendKeys("Admin");
		driver.findElement(By.xpath("(//span[@class='form-hint']/preceding-sibling::input)[2]"))
				.sendKeys("Hum@nhrm123");
		driver.findElement(By.xpath("//input[@name='Submit']")).click();

		WebElement pimLink = driver.findElement(By.id("menu_pim_viewPimModule"));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click();", pimLink);

		WebElement addEmpLnk = driver.findElement(By.id("menu_pim_addEmployee"));
		js.executeScript("arguments[0].click();", addEmpLnk);

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input#firstName")));

		driver.findElement(By.cssSelector("input#firstName")).sendKeys(firstName);
		driver.findElement(By.id("lastName")).sendKeys(lastName);
		driver.findElement(By.id("chkLogin")).click();
		driver.findElement(By.id("user_name")).sendKeys(userName);
		driver.findElement(By.id("user_password")).sendKeys(password);
		driver.findElement(By.id("re_password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@value='Save']")).click();
		Thread.sleep(2000);

		String fullName = driver.findElement(By.xpath("//div[@id = 'profile-pic']/h1")).getText();
		Assert.assertEquals(fullName, firstName + " " + lastName);

		TakesScreenshot ts = (TakesScreenshot) driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(sourceFile, new File(screenshot));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@DataProvider
	public String[][] getData() {

		String[][] data = { { "Nathan", "Luck", "NathanL", "NathanL2020!@#$", "screenshots/HRMS/NewEmployee1.png" },
				{ "Kris", "Orange", "KrisO", "KrisO2020!@#$", "screenshots/HRMS/NewEmployee2.png" },
				{ "James", "Apple", "JamesA", "JamesA2020!@#$", "screenshots/HRMS/NewEmployee3.png" },
				{ "Kate", "Smile", "KateS", "KateS2020!@#$", "screenshots/HRMS/NewEmployee4.png" },
				{ "Hanna", "Super", "HannaS", "HannaS2020!@#$", "screenshots/HRMS/NewEmployee5.png" } };
		return data;
	}
}
