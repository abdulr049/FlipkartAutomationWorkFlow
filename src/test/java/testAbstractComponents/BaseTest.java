package testAbstractComponents;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
	
	protected static WebDriver  driver;
	protected static WebDriverWait wait;

	@BeforeMethod
	public void setup() {

//		ChromeOptions option = new ChromeOptions();
//		option.addArguments("--HEADLESS");
		
		

		driver = new ChromeDriver();

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

	
	public String getScreenshot(String testName) throws IOException {

	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
	    String date = formatter.format(new Date());

	    File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	    String path = System.getProperty("user.dir")
	            + "/screenshots/" + testName + "_" + date + ".png";

	    File destination = new File(path);

	    FileUtils.copyFile(source, destination);

	    return path;
	}

}
