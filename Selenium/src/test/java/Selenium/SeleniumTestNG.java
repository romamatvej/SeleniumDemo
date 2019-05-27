package Selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTestNG {

	private WebDriver driver;
	private String userName="login";
	private String password="password";

	@BeforeMethod
	public void Startup() {
		// Initiate driver
		driver = new ChromeDriver();

		// Go to Gmail
		driver.get("http://gmail.com");

		// Maximize the window
		driver.manage().window().maximize();

		// Clear autofilled and enter username, click next
		driver.findElement(By.id("identifierId")).clear();
		driver.findElement(By.id("identifierId")).sendKeys(userName);
		driver.findElement(By.id("identifierNext")).click();

		final Wait<WebDriver> wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));

		// Clear pass and enter password, click submit
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.id("passwordNext")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\":lp\"]/div/div[2]/span/a")));

		if (driver.getTitle().contains(userName))
			System.out.println("logged in");
		else
			System.out.println("failed logg in");

	}

	@Test(priority = 1)
	public void GoogleLogOut() throws Exception {

		driver.findElement(By.xpath("//*[@id=\"gb\"]/div[2]/div[3]/div/div[2]/div/a")).click();
		driver.findElement(By.xpath("//*[@id=\"gb_71\"]")).click();

		System.out.println(driver.getTitle());
	}
	@AfterMethod
	public void ShutDown() {
		driver.close();
	}

}