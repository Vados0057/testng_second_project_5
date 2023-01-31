package scripts;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.UnitedBasePage;
import utilities.ConfigReader;
import utilities.Driver;

public class UnitedBase {
    WebDriver driver;
    UnitedBasePage unitedBasePage;
    UnitedMainMenuNavigationTest unitedMainMenuNavigationTest;

    @BeforeMethod
    public void setup() {
        driver = Driver.getDriver();
        driver.get(ConfigReader.getProperty("appURL"));
        unitedBasePage = new UnitedBasePage();
    }

    @AfterMethod
    public void teardown() {
        Driver.quitDriver();
    }
}
