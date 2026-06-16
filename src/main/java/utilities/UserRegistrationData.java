package utilities;

import java.util.HashMap;

import org.testng.annotations.DataProvider;

public class UserRegistrationData {

    @DataProvider(name = "userData")
    public Object[][] userDetails() {

        HashMap<String, String> data = new HashMap<>();

        data.put("name", "Abdul");
        data.put("password", "Abdul@123");
        data.put("email", "abdul" + System.currentTimeMillis() + "@gmail.com");

        data.put("day", "11");
        data.put("month", "November");
        data.put("year", "1992");

        data.put("newsletter", "true");
        data.put("specialOffers", "true");

        data.put("firstName", "Abdul");
        data.put("lastName", "Rahaman");
        data.put("company", "Ascent Business");
        data.put("address1", "Radhe Wali Gali, Gali No. 17");
        data.put("address2", "Jagner Road Agra");
        data.put("country", "India");
        data.put("state", "Uttar Pradesh");
        data.put("city", "Agra");
        data.put("zipCode", "282001");
        data.put("mobileNumber", "8882762490");

        return new Object[][] { { data } };
    }
}