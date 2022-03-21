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

public class mouseHover {
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

		wd.get("https://demoqa.com/tool-tips");

		wd.manage().window().maximize();
	}

	@Test
	public void moveTo() {
		// intantiate the action class
		action = new Actions(wd);

		// find element
		WebElement mouseHover = wd.findElement(By.id("toolTipButton"));
		// Perform action
		action.moveToElement(mouseHover).perform();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Assertion check
		Assert.assertEquals( "buttonToolTip",mouseHover.getAttribute("aria-describedby"));
	}
}
