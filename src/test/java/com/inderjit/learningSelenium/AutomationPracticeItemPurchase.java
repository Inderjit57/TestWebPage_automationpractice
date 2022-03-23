package com.inderjit.learningSelenium;

import java.awt.Desktop.Action;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

	// Create instance of Javascript executor
	JavascriptExecutor je;
//	je = (JavascriptExecutor) wd;
//	je.executeScript("arguments[0].scrollIntoView(true);",iframe);

//	 Using explicit Wait to find elements
//	driverWait = new WebDriverWait(wd, 10);
//	driverWait.until(ExpectedConditions
//			.visibilityOfElementLocated(By.id(".box-info-product p input[id='quantity_wanted']")));

	@BeforeMethod
	public void setupDriver() {
		// Setting up the ChromeDriver path
		System.setProperty("webdriver.chrome.driver", "C:\\Everything\\lib\\chromedriver\\chromedriver.exe");
		wd = new ChromeDriver();
		wd.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

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

		// switch to iframe
		WebElement iframe = wd.findElement(By.cssSelector(
				"iframe[src='http://automationpractice.com/index.php?id_product=1&controller=product&content_only=1']"));

		wd.switchTo().frame(iframe);

		// 5. Quantity added
		WebElement addQuantity = wd.findElement(By.cssSelector("#quantity_wanted_p :nth-of-type(2) span i"));
		addQuantity.click();

		// 6. Select size
		WebElement selectSize = wd.findElement(By.cssSelector(".attribute_list div select option:nth-of-type(3)"));
		selectSize.click();

		// 7. Add to cart
		WebElement addToCart = wd.findElement(By.cssSelector(".box-cart-bottom button[type='submit']"));
		addToCart.click();
		// switch back to default content
		wd.switchTo().defaultContent();

		// 8. assert Message
		WebElement cartMessage = wd.findElement(By.cssSelector("#layer_cart > div[class='clearfix']"));

		// Using JavascriptExecutor to scroll to the tab
		je = (JavascriptExecutor) wd;
		je.executeScript("arguments[0].scrollIntoView(true);", cartMessage);

		WebElement successfullCartMessage = wd.findElement(
				By.cssSelector(".layer_cart_product.col-xs-12.col-md-6 :nth-of-type(1) i[class='icon-ok']"));
		boolean isTextDisplayed = successfullCartMessage.isDisplayed();
		Assert.assertEquals(isTextDisplayed, false);

		// 9. Assert The QTY and Product
		WebElement productTitle = wd
				.findElement(By.cssSelector("#layer_cart div > div  div span[id='layer_cart_product_title']"));
		boolean isProductTitleTextDisplayed = productTitle.isDisplayed();
		Assert.assertEquals(isProductTitleTextDisplayed, false);

		// 10. Proceed to checkout
		WebElement checkOutBtn = wd
				.findElement(By.cssSelector(".layer_cart_cart.col-xs-12.col-md-6 :nth-of-type(4) a"));
		checkOutBtn.click();

		// 11. Total Prize
		WebElement totalPrice = wd.findElement(By.cssSelector("#total_price_container"));
		String getTotalPrice = totalPrice.getText();
		System.out.println("total price :" + getTotalPrice);

		Assert.assertEquals(getTotalPrice, "$35.02");

		// 12. Proceed to checkout
		WebElement checkOutBtn2 = wd
				.findElement(By.cssSelector(".cart_navigation.clearfix a[title='Proceed to checkout'] span i"));
		checkOutBtn2.click();

		// 13. Write Text in the box
		WebElement textBox = wd.findElement(By.cssSelector("textarea[name='message']"));
		textBox.sendKeys("Blah blah blah....blah!");

		// 14. Proceed to checkout
		WebElement checkOutBtn3 = wd.findElement(By.cssSelector(".cart_navigation.clearfix button[type='submit']"));
		checkOutBtn3.click();

		// 15. Click the checkbox
		WebElement checkBox = wd.findElement(By.cssSelector(".checkbox div input[type='checkbox']"));
		checkBox.click();

		// 16. proceed to checkout
		WebElement checkOutBtn4 = wd.findElement(By.cssSelector(".cart_navigation.clearfix button[type='submit']"));
		checkOutBtn4.click();

		// 17. Pay with bank
		WebElement checkPayWithBank = wd.findElement(By.cssSelector(".bankwire"));
		checkPayWithBank.click();

		// 18 Bank Wire Payment -assertion check
		WebElement bankWireHeading = wd.findElement(By.cssSelector(".page-subheading"));
		String getBankWireText = bankWireHeading.getText();
		System.out.println("Wire payment title: " + getBankWireText);
		Assert.assertEquals(getBankWireText, "BANK-WIRE PAYMENT.");

		// 19. Confirm order
		WebElement confirmOrderbtn = wd.findElement(By.cssSelector("p> button[type='submit']"));
		confirmOrderbtn.click();

		// 20. Order confirmation
		WebElement orderConfirmationMessage = wd.findElement(By.cssSelector(".cheque-indent strong[class='dark']"));
		String confirmMessage = orderConfirmationMessage.getText();

		Assert.assertEquals(confirmMessage, "Your order on My Store is complete.");

	}

}
