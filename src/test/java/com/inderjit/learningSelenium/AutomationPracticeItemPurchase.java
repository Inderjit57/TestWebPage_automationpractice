package com.inderjit.learningSelenium;

import java.awt.Desktop.Action;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AutomationPracticeItemPurchase {

	WebDriver wd;
	WebDriverWait driverWait;
	Actions actions;

	@BeforeMethod
	public void setupDriver() {
		// Setting up the ChromeDriver path
		System.setProperty("webdriver.chrome.driver", "C:\\Everything\\lib\\chromedriver\\chromedriver.exe");

		// Initialize the webDriver using the constructor of chromeDriver:
		// ChromeDriver Extends RemoteWebDriver . RemoteWebDriver implements WebDriver.
		wd = new ChromeDriver();

		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		wd.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		wd.manage().window().maximize();
	}

	@Test
	public void signInCredentials() {

		// 1. Login portal
		WebElement emailAddressInput = wd.findElement(By.cssSelector(".col-xs-12.col-sm-6 div input[id='email']"));
		emailAddressInput.sendKeys("famous123@gmail.com");
		WebElement passwordInput = wd.findElement(By.cssSelector(".form-group span input[id='passwd']"));
		passwordInput.sendKeys("famous123");

		WebElement signInClickBtn = wd.findElement(By.cssSelector("#SubmitLogin"));
		signInClickBtn.click();

		// 2. Assertion check
		WebElement accountName = wd.findElement(By.cssSelector(".header_user_info a[class='account']"));
		String accountNameText = accountName.getText();
		System.out.println("Account Name: " + accountNameText);
		Assert.assertEquals(accountNameText, "Inderjit Singh");

		// 3. Click on Women
		WebElement womenBtn = wd.findElement(By.cssSelector("a[title='Women']"));
		womenBtn.click();

		WebElement moveToItemFadedShortSleeve = wd.findElement(By.cssSelector(
				"li[class='ajax_block_product col-xs-12 col-sm-6 col-md-4 first-in-line first-item-of-tablet-line first-item-of-mobile-line']"));

		// 4. Quick view:
		// Intantiate Action Class to perform mouse hover
		actions = new Actions(wd);
		actions.moveToElement(moveToItemFadedShortSleeve).perform();

		WebElement mouseHoverToShortSleeve = wd.findElement(
				By.cssSelector(".product_list.grid.row li:nth-of-type(1) div div div a[class='quick-view']"));
		mouseHoverToShortSleeve.click();

		WebElement fancyBox = wd.findElement(By.cssSelector(".fancybox-overlay.fancybox-overlay-fixed"));
		
		wd.switchTo().frame(0)
		actions = new Actions(wd);
		actions.moveToElement(fancyBox).perform();
		

		// 5. Quantity added
//		 Using explicit Wait to find elements
//		driverWait = new WebDriverWait(wd, 10);
//		driverWait.until(ExpectedConditions
//				.visibilityOfElementLocated(By.id(".box-info-product p input[id='quantity_wanted']")));
		WebElement addQuantity = wd.findElement(By.cssSelector(".product_attributes.clearfix p input[id='quantity_wanted']"));
		addQuantity.sendKeys("2");

		// 6. Select size
		WebElement selectSize = wd.findElement(By.cssSelector(".attribute_list div select option:nth-of-type(3)"));
		selectSize.click();

		// 7. Add to cart
		WebElement addToCart = wd.findElement(By.cssSelector(".box-cart-bottom button[type='submit']"));
		addToCart.click();

	}

}
