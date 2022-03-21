package WebCommands;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class waitCommands {
	WebDriver wd;
	WebDriverWait driverWait;	// Intiantiating Wait Class
	
	@BeforeMethod
	public void setupDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\Everything\\lib\\chromedriver\\chromedriver.exe");
		wd = new ChromeDriver();

		driverWait = new WebDriverWait(wd, 10);
		wd.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
	}
	
	@Test
	public void signUpTest() {
		WebElement emailInput = wd.findElement(By.cssSelector("input[id='email_create']"));
		WebElement clickSubmit = wd.findElement(By.cssSelector("#SubmitCreate"));

		Random random = new Random();
		emailInput.sendKeys("famous" + random.nextInt() + "@gmail.com");
		clickSubmit.click();
		
		//Explicit wait if couldn't find element
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".radio-inline label input[id='id_gender1']")));
	
	}

}
