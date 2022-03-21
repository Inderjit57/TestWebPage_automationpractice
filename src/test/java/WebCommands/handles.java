package WebCommands;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class handles {
	WebDriver wd;
//	WebDriverWait driverWait;

	@BeforeMethod
	public void setupDriver() {
		// Setting up the ChromeDriver path
		System.setProperty("webdriver.chrome.driver", "C:\\Everything\\lib\\chromedriver\\chromedriver.exe");

		// Initialize the webDriver using the constructor of chromeDriver:
		// ChromeDriver Extends RemoteWebDriver . RemoteWebDriver implements WebDriver.
		wd = new ChromeDriver();

		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Using explicit Wait to find elements
//		driverWait = new WebDriverWait(wd, 10);

		wd.get("https://demoqa.com/browser-windows");
		wd.manage().window().maximize();
	}
	@Test
	public void identifyWindowWithSelenium() {
		String parentHandle = wd.getWindowHandle();
		
		WebElement newTabBtn = wd.findElement(By.id("tabButton"));
	}

}
