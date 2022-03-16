package com.inderjit.learningSelenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestSignUp {
	WebDriver wd;

	@BeforeMethod
	public void setupDriver() {
		// Setting up the ChromeDriver path
		System.setProperty("webdriver.chrome.driver", "C:\\Everything\\lib\\chromedriver\\chromedriver.exe");

		// Initialize the webDriver using the constructor of chromeDriver:
		// ChromeDriver Extends RemoteWebDriver . RemoteWebDriver implements WebDriver.
		wd = new ChromeDriver();
		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		wd.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");

		wd.manage().window().maximize();
	}

	@Test
	public void signUpTest() {
		WebElement emailInput = wd.findElement(By.cssSelector("input[id='email_create']"));
		WebElement clickSubmit = wd.findElement(By.cssSelector("#SubmitCreate"));
		emailInput.sendKeys("inder61@gmail.com");
		clickSubmit.click();

		// Signup Page: FINDING ELEMENTS FROM THE WEBPAGE:

		WebElement firstNameInput = wd.findElement(By.cssSelector("#customer_firstname"));
		WebElement lastNameInput = wd.findElement(By.cssSelector("input[id='customer_lastname']"));
		WebElement passwordInput = wd.findElement(By.cssSelector("input[id='passwd']"));
		WebElement dayInput = wd.findElement(By.cssSelector("select[name='days']"));
		WebElement monthInput = wd.findElement(By.cssSelector("select[name='months']"));
		WebElement yearInput = wd.findElement(By.cssSelector("select[name='years']"));
		WebElement radioElementMr = wd.findElement(By.cssSelector(".radio-inline label input[id='id_gender1']"));
		WebElement companyInput = wd.findElement(By.cssSelector("input[name='company']"));
		WebElement address1Input = wd.findElement(By.cssSelector("input[name='address1']"));
		WebElement cityInput = wd.findElement(By.cssSelector("input[name='city']"));
		WebElement stateInput = wd.findElement(By.cssSelector("select[name='id_state']"));
		WebElement zipCodeInput = wd.findElement(By.cssSelector("input[name='postcode']"));
		WebElement countryInput = wd.findElement(By.cssSelector("select[name='id_country']"));
		WebElement otherInfoInput = wd.findElement(By.cssSelector("textarea[name='other']"));
		WebElement homePhoneInput = wd.findElement(By.cssSelector("input[name='phone']"));
		WebElement mobilePhoneInput = wd.findElement(By.cssSelector("input[name='phone_mobile']"));
		WebElement aliasInput = wd.findElement(By.cssSelector("input[name='alias']"));

		// SENDING KEYS TO THE ELEMENTS FROM TOP TO BOTTOM

		// WebElement chooseRadioElementMRs =
		// wd.findElement(By.cssSelector(".radio-inline label input[id='id_gender2']"));
		radioElementMr.click();
		firstNameInput.sendKeys("Inderjit");
		lastNameInput.sendKeys("Singh");
		passwordInput.sendKeys("Inder1234!");

		// Select Day/Month/Year
		Select selectDay = new Select(dayInput);
		Select selectMonth = new Select(monthInput);
		Select selectYear = new Select(yearInput);
		selectDay.selectByValue("5");
		selectMonth.selectByValue("5");
		selectYear.selectByValue("1941");

		wd.findElement(By.cssSelector("#newsletter")).click();
		companyInput.sendKeys("Penguin Inc");
		address1Input.sendKeys("123 automation practice drive");
		cityInput.sendKeys("Brampton");

		Select stateSelection = new Select(stateInput);
		stateSelection.selectByValue("7");

		zipCodeInput.sendKeys("06095");

		Select country = new Select(countryInput);
		country.selectByValue("21");
		otherInfoInput.sendKeys("705-000-2222");
		mobilePhoneInput.sendKeys("555-666-8888");
		aliasInput.sendKeys("300 Mars Blvd West");
		wd.findElement(By.cssSelector("#submitAccount")).click();

		// After Signup
		System.out.println(wd.getTitle());
		
		WebElement loginMessage= wd.findElement(By.cssSelector(".center_column p[class='info-account']"));
		String getLoginText = loginMessage.getText();
		Assert.assertEquals(getLoginText,"Welcome to your account. Here you can manage all of your personal information and orders." , "Not a same Login Message");
		//	
	}

	@AfterMethod
	public void closeBrowser() {
		wd.quit();
	}
}
