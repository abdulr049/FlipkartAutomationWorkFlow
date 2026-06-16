package automationWorkFlows;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class FlipkartProductOrder {

	@Test
	public void productOrder() {

		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		try {

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			driver.get("https://www.flipkart.com/?login=false");

			// Search Laptop
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".nw1UBF"))).sendKeys("Laptop");

			driver.findElement(By.cssSelector(".b3wTlE")).click();

			// Close popup if displayed
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".XFwMiH"))).click();
			} catch (Exception e) {
				System.out.println("No popup found.");
			}

			// Expand Brand filter
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[normalize-space()='Brand']"))).click();

			// Select HP brand
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[text()='Brand']/following::div[contains(@class,'buvtMR')]")));

			List<WebElement> brands = driver
					.findElements(By.xpath("//div[text()='Brand']/following::div[contains(@class,'buvtMR')]"));

			for (WebElement brand : brands) {

				if (brand.getText().trim().equalsIgnoreCase("HP")) {

					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", brand);

					((JavascriptExecutor) driver).executeScript("arguments[0].click();", brand);

					break;
				}
			}

			// Wait for products to reload
			Thread.sleep(3000);

			// Locate required laptop directly
			WebElement laptop = wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//div[contains(text(),'HP 14 AI PC Intel Core Ultra 7 155H')]")));

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", laptop);

			wait.until(ExpectedConditions.visibilityOf(laptop));

			((JavascriptExecutor) driver).executeScript("arguments[0].click();", laptop);

			System.out.println("Laptop selected successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}

		String parentWindow = driver.getWindowHandle();

		Set<String> windows = driver.getWindowHandles();
		for (String window : windows) {
			if (!window.equals(parentWindow)) {
				driver.switchTo().window(window);
				System.out.println("Child Page Title: " + driver.getCurrentUrl());
				break;
			}
		}

	}
}