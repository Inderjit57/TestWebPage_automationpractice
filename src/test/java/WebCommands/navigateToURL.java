package WebCommands;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class navigateToURL {
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

		// Go to URL
		wd.get("https://demoqa.com/tool-tips");

		wd.manage().window().maximize();
	}

	@Test
	public void navigate() {
		wd.navigate().to("https://demoqa.com/buttons");

		// move forward
		wd.navigate().forward();

		// Reload
		wd.navigate().refresh();
	}
}
