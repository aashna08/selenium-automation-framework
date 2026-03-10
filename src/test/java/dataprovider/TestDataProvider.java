package dataprovider;

import org.testng.annotations.DataProvider;
import utils.ExcelUtils;

public class TestDataProvider {

    /*@DataProvider(name = "registerUserData")
    public static Object[][] registerUserData() {

        return ExcelUtils.getTestData(
                "src/test/resources/testdata/RegisterUserData.xlsx",
                "RegisterUser"
        );
    }*/

    @DataProvider(name = "registerUserData")
    public static Object[][] registerUserData() {

        return new Object[][] {
                {
                        "Test User",
                        "test.user",
                        "Hello@783",
                        2, 2, 5,
                        "Test",
                        "One",
                        "Alert",
                        "h5",
                        "h5",
                        "India",
                        "HP",
                        "MP",
                        "173025",
                        "9999999999"
                }
        };
    }

    @DataProvider(name = "loginValidData")
    public static Object[][] loginValidData(){
        return new Object[][]{
                {
                    "automation828@test.com","Hello@783","Automation User"
                }
        };
    }

    @DataProvider(name = "invalidLoginData")
    public static Object[][] invalidLoginData()
    {
        return new Object[][]{
                {"automation728@test.com","Hello@783"}
        };
    }

    @DataProvider(name = "existingUserData")
    public static Object[][] existingUserData()
    {
        return new Object[][]{
                {"Jamie One","automation828@test.com"}
        };
    }

    @DataProvider(name = "ContactUsData")
    public static Object[][] ContactUsData()
    {
        return new Object[][]{
                {"Jamie One","automation8289@test.com","Test Subject","Test Message"}
        };
    }

    @DataProvider(name = "viewCategoryData")
    public static Object[][] viewCategoryData()
    {
        return new Object[][]{
                {"Women","Dress"},
                {"Men","Tshirts"}
        };
    }

    @DataProvider(name = "viewBrandData")
    public static Object[][] viewBrandData()
    {
        return new Object[][]{
                {"Polo"}
        };
    }




}

