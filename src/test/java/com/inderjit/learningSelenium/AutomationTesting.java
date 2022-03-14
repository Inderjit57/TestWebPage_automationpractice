package com.inderjit.learningSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AutomationTesting {
	WebDriver wd;

	@BeforeMethod
	public void setupDriver() {
		// Setting up the ChromeDriver path
		System.setProperty("webdriver.chrome.driver", "C:\\Everything\\lib\\chromedriver\\chromedriver.exe");

		// Initialize the webDriver using the constructor of chromeDriver:
		// ChromeDriver Extends RemoteWebDriver . RemoteWebDriver implements WebDriver.
		wd = new ChromeDriver();

		wd.get("http://automationpractice.com/index.php?controller=contact");

		wd.manage().window().maximize();
	}

	@Test
	public void sendKeys() {
		// Identify an element
		WebElement subjectHeadingInput = wd.findElement(By.id("id_contact"));
		WebElement emailAddressInput = wd.findElement(By.id("email"));
		WebElement orderReferenceInput = wd.findElement(By.id("id_order"));
		WebElement messageInput = wd.findElement(By.xpath("//textarea[@name='message']"));
		WebElement submitInput = wd.findElement(By.id("submitMessage"));

		Select select = new Select(subjectHeadingInput);
		select.selectByIndex(1);
		// perform action

		emailAddressInput.sendKeys("abcd@gmail.com");
		orderReferenceInput.sendKeys("123455");
		messageInput.sendKeys("I want to order kutra pajama. But its not in the list");

		submitInput.click();
	}

	@AfterMethod
	public void closeBrowser() {
		wd.quit();
	}
}
