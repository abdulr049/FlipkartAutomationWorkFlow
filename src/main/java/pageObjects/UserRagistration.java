package pageObjects;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class UserRagistration extends AbstractComponents {

	WebDriver driver;

	public UserRagistration(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}

	By waitForLoginButtonToBeclickable = By.xpath("//a[@href='/login']");

	@FindBy(xpath = "//a[@href='/login']")
	WebElement loginButton;

	By waitForSignUpUserButtonToBeclickable = By.xpath("//input[@placeholder='Name']");

	@FindBy(xpath = "//input[@placeholder='Name']")
	WebElement userNameInput;

	@FindBy(xpath = "//input[@data-qa='signup-email']")
	WebElement userEmailInput;

	@FindBy(xpath = "//button[@data-qa='signup-button']")
	WebElement signUpButton;

	By waitForGenderButton = By.id("id_gender1");

	@FindBy(id = "id_gender1")
	WebElement genderButton;

	@FindBy(id = "password")
	WebElement password;

	@FindBy(id = "days")
	WebElement day;

	@FindBy(id = "months")
	WebElement month;

	@FindBy(id = "years")
	WebElement year;

	@FindBy(id = "newsletter")
	WebElement newsletter;

	@FindBy(id = "optin")
	WebElement partnerOption;

	@FindBy(id = "first_name")
	WebElement firstName;

	@FindBy(id = "last_name")
	WebElement lastName;

	@FindBy(id = "company")
	WebElement company;

	@FindBy(id = "address1")
	WebElement address1;

	@FindBy(id = "address2")
	WebElement address2;

	@FindBy(id = "country")
	WebElement country;

	@FindBy(id = "state")
	WebElement state;

	@FindBy(id = "city")
	WebElement city;

	@FindBy(id = "zipcode")
	WebElement zipcode;

	@FindBy(id = "mobile_number")
	WebElement mobileNnumber;

	@FindBy(xpath = "//button[@data-qa='create-account']")
	WebElement createAccountButton;

	By errorMessageForExistingEmailSignup = By.xpath("//p[text()='Email Address already exist!']");

	@FindBy(xpath = "//p[text()='Email Address already exist!']")
	WebElement errorMessage;

	

	public void userRegister(HashMap<String, String> data) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		waitForElementToBeClickable(waitForLoginButtonToBeclickable);
		loginButton.click();

		waitForVisibilityOfElementLocated(waitForSignUpUserButtonToBeclickable);

		userNameInput.sendKeys(data.get("name"));
		userEmailInput.sendKeys(data.get("email"));

		js.executeScript("arguments[0].scrollIntoView(true);", signUpButton);
		signUpButton.click();

		waitForElementToBeClickable(waitForGenderButton);
		genderButton.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
		password.clear();
		password.sendKeys(data.get("password"));

		Select selectDay = new Select(day);
		selectDay.selectByVisibleText(data.get("day"));

		Select selectMonth = new Select(month);
		selectMonth.selectByVisibleText(data.get("month"));

		Select selectYear = new Select(year);
		selectYear.selectByVisibleText(data.get("year"));

		js.executeScript("arguments[0].scrollIntoView(true);", newsletter);
		if (!newsletter.isSelected())
			newsletter.click();

		js.executeScript("arguments[0].scrollIntoView(true);", partnerOption);
		if (!partnerOption.isSelected())
			partnerOption.click();

		firstName.sendKeys(data.get("firstName"));
		lastName.sendKeys(data.get("lastName"));
		company.sendKeys(data.get("company"));
		address1.sendKeys(data.get("address1"));
		address2.sendKeys(data.get("address2"));

		Select countryDropdown = new Select(country);
		countryDropdown.selectByVisibleText(data.get("country"));

		state.sendKeys(data.get("state"));
		city.sendKeys(data.get("city"));
		zipcode.sendKeys(data.get("zipCode"));
		mobileNnumber.sendKeys(data.get("mobileNumber"));

		js.executeScript("arguments[0].scrollIntoView(true);", createAccountButton);
		createAccountButton.click();
	}

	public String userRegisterWithExistingEmail(HashMap<String, String> data) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Click Signup/Login
		waitForElementToBeClickable(waitForLoginButtonToBeclickable);
		loginButton.click();

		// Existing User Details
		userNameInput.sendKeys(data.get("name"));
		userEmailInput.sendKeys("abdul1781334787016@gmail.com");

		js.executeScript("arguments[0].scrollIntoView(true);", signUpButton);
		signUpButton.click();

		waitForVisibilityOfElementLocated(errorMessageForExistingEmailSignup);

		// Validate Error Message
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Email Address already exist!']")));

		String actualMessage = errorMessage.getText();

		
		return actualMessage;
	}
}