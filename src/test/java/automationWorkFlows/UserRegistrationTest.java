package automationWorkFlows;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageObjects.UserRagistration;
import testAbstractComponents.BaseTest;
import utilities.UserRegistrationData;

@Listeners(testAbstractComponents.Listeners.class)

public class UserRegistrationTest extends BaseTest {

    @Test(dataProvider = "userData", dataProviderClass = UserRegistrationData.class)
    public void userRegistrationForm(HashMap<String, String> data) {

        UserRagistration userDetails = new UserRagistration(driver);

        userDetails.userRegister(data);
    }
    
    public class UserRegistrationWithExistingEmailTest extends BaseTest {

        @Test(dataProvider = "userData", dataProviderClass = UserRegistrationData.class)
        public void userRegistrationForm(HashMap<String, String> data) {

            UserRagistration userDetails = new UserRagistration(driver);
            String actualMessage=userDetails.userRegisterWithExistingEmail(data);
            Assert.assertEquals(actualMessage, "Email Address already exist!");
           
        }
    }  
}