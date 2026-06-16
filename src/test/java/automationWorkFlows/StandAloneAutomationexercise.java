package automationWorkFlows;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import testAbstractComponents.BaseTest;

public class StandAloneAutomationexercise extends BaseTest{

	WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	public void setup() {

		ChromeOptions option = new ChromeOptions();
		option.addArguments("--HEADLESS");

		driver = new ChromeDriver(option);

		driver.get("https://automationexercise.com/");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	@AfterMethod
	public void tearDown() {

		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void userRegister() {

		// Click Signup/Login
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/login']"))).click();

		// Enter Name
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Name']")))
				.sendKeys("Abdul Rahaman");

		// Generate unique email
		String email = "abdul" + System.currentTimeMillis() + "@gmail.com";

		driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys(email);

		// Click Signup button
		driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();

		// Select Gender
		wait.until(ExpectedConditions.elementToBeClickable(By.id("id_gender1"))).click();

		// Enter Password
		WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));

		password.clear();
		password.sendKeys("Abdul@123");

		// Date of Birth
		Select selectDay = new Select(driver.findElement(By.id("days")));
		selectDay.selectByValue("11");

		Select selectMonth = new Select(driver.findElement(By.id("months")));
		selectMonth.selectByVisibleText("November");

		Select selectYear = new Select(driver.findElement(By.id("years")));
		selectYear.selectByValue("1992");

		// Newsletter
		WebElement newsletter = driver.findElement(By.id("newsletter"));

		if (!newsletter.isSelected()) {
			newsletter.click();
		}

		// Special Offers
		WebElement partners = driver.findElement(By.id("optin"));

		if (!partners.isSelected()) {
			partners.click();
		}

		// Address Information
		driver.findElement(By.id("first_name")).sendKeys("Abdul");

		driver.findElement(By.id("last_name")).sendKeys("Rahaman");

		driver.findElement(By.id("company")).sendKeys("Ascent Business");

		driver.findElement(By.id("address1")).sendKeys("Radhe Wali Gali, Gali No. 17");

		driver.findElement(By.id("address2")).sendKeys("Jagner Road Agra");

		Select selectCountry = new Select(driver.findElement(By.id("country")));

		selectCountry.selectByVisibleText("India");

		driver.findElement(By.id("state")).sendKeys("Uttar Pradesh");

		driver.findElement(By.id("city")).sendKeys("Agra");

		driver.findElement(By.id("zipcode")).sendKeys("282001");

		driver.findElement(By.id("mobile_number")).sendKeys("8882762490");

		// Create Account
		driver.findElement(By.xpath("//button[@data-qa='create-account']")).click();

		// Validation
		WebElement successMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@data-qa='account-created']")));

		String actualMessage = successMessage.getText().trim();

		Assert.assertEquals(actualMessage, "ACCOUNT CREATED!");
	}

	@Test
	public void registerWithExistingEmail() {

		// Click Signup/Login
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/login']"))).click();

		// Existing User Details
		driver.findElement(By.xpath("//input[@data-qa='signup-name']")).sendKeys("Abdul Rahaman");

		driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys("abdul1781334787016@gmail.com");

		driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();

		// Validate Error Message
		WebElement errorMessage = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Email Address already exist!']")));

		String actualMessage = errorMessage.getText();

		Assert.assertEquals(actualMessage, "Email Address already exist!");
	}

	@Test
	public void loginWithValidCredentials() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/login']"))).click();

		// Existing User Details
		driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("abdul1781334787016@gmail.com");

		driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("Abdul@123");

		driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();
	}

	@Test
	public void loginWithInValidCredentials() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/login']"))).click();

		// Existing User Details
		driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("abdul431334787016@gmail.com");

		driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("Abdul@123");

		driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();

		String errorMessage = driver.findElement(By.xpath("//p[text()='Your email or password is incorrect!']"))
				.getText().trim();

		Assert.assertEquals(errorMessage, "Your email or password is incorrect!");

	}

	@Test
	public void logOut() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/login']"))).click();

		// Existing User Details
		driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("abdul1781334787016@gmail.com");

		driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("Abdul@123");

		driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/logout']")));

		driver.findElement(By.xpath("//a[@href='/logout']")).click();

		String pageUrl = driver.getCurrentUrl();

		Assert.assertEquals(pageUrl, "https://automationexercise.com/login");

	}

	@Test
	public List<WebElement> products() {

		driver.findElement(By.xpath("//a[@href='/products']")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".productinfo")));

		List<WebElement> products = driver.findElements(By.cssSelector(".productinfo"));

		return products;

	}

	@Test
	public void searchProduct() {

		// Click Products
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/products']"))).click();

		// Wait for search box
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_product")));

		// Enter product name
		driver.findElement(By.id("search_product")).sendKeys("Blue Top");

		// Click Search button using JavaScript
		WebElement searchBtn = driver.findElement(By.id("submit_search"));

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchBtn);

		// Wait for products
		List<WebElement> products = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".product-image-wrapper")));

		boolean found = false;

		for (WebElement product : products) {

			String productName = product.findElement(By.tagName("p")).getText();

			if (productName.equalsIgnoreCase("Blue Top")) {

				// Hover over product
				Actions actions = new Actions(driver);
				actions.moveToElement(product).perform();

				// Add to Cart
				WebElement addToCart = product.findElement(By.xpath(".//a[contains(text(),'Add to cart')]"));

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCart);

				System.out.println("Product Found: " + productName);

				found = true;
				break;
			}
		}

		Assert.assertTrue(found, "Product not found");

		// Wait for View Cart popup
		WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//u[text()='View Cart']")));

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewCart);

		// Validation
		Assert.assertTrue(driver.getCurrentUrl().contains("view_cart"), "Cart page not opened");
	}

	@Test
	public void invalidSearch() throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/products']"))).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_product")));

		driver.findElement(By.id("search_product")).sendKeys("xyzabc");

		WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit_search")));

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchBtn);

		Thread.sleep(2000);

		List<WebElement> products = driver.findElements(By.cssSelector(".productinfo"));

		System.out.println("Products found : " + products.size());

		Assert.assertEquals(products.size(), 0);
	}

	@Test
	public void addTwoProduct() {

		// Step 1: Open Products page
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/products']"))).click();

		// Step 2: Get products
		List<WebElement> products = wait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".product-image-wrapper")));

		// Step 3: Loop products
		for (WebElement product : products) {

			String productName = product.findElement(By.cssSelector(".productinfo p")).getText();

			if (productName.equalsIgnoreCase("Blue Top") || productName.equalsIgnoreCase("Sleeveless Dress")) {

				Actions actions = new Actions(driver);
				actions.moveToElement(product).perform();

				WebElement addToCart = product.findElement(By.cssSelector(".add-to-cart"));

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCart);

				// WAIT FOR POPUP
				WebElement continueBtn = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Continue Shopping']")));

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueBtn);

				// 🔥 IMPORTANT FIX: wait for overlay to disappear
				wait.until(ExpectedConditions
						.invisibilityOfElementLocated(By.xpath("//button[text()='Continue Shopping']")));
			}
		}

		// Step 4: Go to cart
		WebElement cart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/view_cart']")));

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", cart);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cart_info_table")));

		// Step 3: Get product names from cart
		List<WebElement> cartProducts = driver.findElements(By.xpath("//td[@class='cart_description']//a"));

		System.out.println("=== PRODUCTS IN CART ===");

		for (WebElement product : cartProducts) {
			System.out.println(product.getText());
		}
	}
}