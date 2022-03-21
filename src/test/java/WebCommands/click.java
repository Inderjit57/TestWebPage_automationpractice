package WebCommands;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class click {
	WebDriver wd;
	Actions action;

	@BeforeMethod
	public void setupDriver() {
		// Setting up the ChromeDriver path
		System.setProperty("webdriver.chrome.driver", "C:\\Everything\\lib\\chromedriver\\chromedriver.exe");

		// Initialize the webDriver using the constructor of chromeDriver:
		// ChromeDriver Extends RemoteWebDriver . RemoteWebDriver implements WebDriver.
		wd = new ChromeDriver();
		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		wd.get("https://demoqa.com/buttons");

		wd.manage().window().maximize();
	}

	@Test
	public void rightClick() {
		// intantiate the action class
		action = new Actions(wd);

		// find element
		WebElement rightClickBtn = wd.findElement(By.id("rightClickBtn"));
		// Perform action
		action.contextClick(rightClickBtn).perform();
		// Assertion check
		WebElement rightClickMessageAppears = wd.findElement(By.id("rightClickMessage"));
		Assert.assertEquals(rightClickMessageAppears.getText(), "You have done a right click");
	}

	@Test
	public void doubleClick() {
		// intantiate the action class
		action = new Actions(wd);

		// find element
		WebElement doubleClickBtn = wd.findElement(By.id("doubleClickBtn"));
		// Perform action
		action.contextClick(doubleClickBtn).perform();
		// Assertion check
		WebElement doubleClickMessageAppears = wd.findElement(By.id("doubleClickMessage"));
		Assert.assertEquals(doubleClickMessageAppears.getText(), "You have done a double click");
	}

	@Test
	public void click() {
		// intantiate the action class
		action = new Actions(wd);

		// find element
		WebElement clickBtn = wd.findElement(By.id("div.col-12 div>div.mt-4:nth-of-type(3) button"));
		// Perform action
		action.click(clickBtn).perform();
		// Assertion check
		WebElement clickMessageAppears = wd.findElement(By.id("dynamicClickMessage"));
		Assert.assertEquals(clickMessageAppears.getText(), "You have done a dynamic click");
	}

	@BeforeMethod
	public void teardown() {
		wd.quit();
	}
}
