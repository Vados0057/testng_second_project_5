package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class UnitedBasePage {
    public UnitedBasePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "div>a[id*=headerNav]")
    public List<WebElement> mainMenuNavigationBar;

    @FindBy(css = "ul[class*='3RNBj'] li")
    public List<WebElement> bookTravelMenu;

    @FindBy(css = "span[class*='checkmark--2DHJD']")
    public List<WebElement> flightTypesLabels;

    @FindBy(css = "fieldset[name='flightType'] input")
    public List<WebElement> flightTypesInputs;

    public WebElement getButton(int n){
        return flightTypesInputs.get(n);
    }

    @FindBy(id = "award")
    public WebElement milesInput;

    @FindBy(id = "flexibleDates")
    public WebElement flexibleDatesInput;

    @FindBy(css = "label[for='award']")
    public WebElement milesLabel;

    @FindBy(id = "flexDatesLabel")
    public WebElement flexibleDatesLabel;

    @FindBy(id = "bookFlightOriginInput")
    public WebElement departurePlace;

    @FindBy(id = "bookFlightDestinationInput")
    public WebElement arrivalPlace;

    @FindBy(id = "passengerSelector")
    public WebElement travelersOption;

    @FindBy(xpath = "//button[@aria-label='Substract one Adult']")
    public WebElement addAdult;

    @FindBy(id = "cabinType")
    public WebElement cabinTypeDropdown;

    @FindBy(id = "ul[tabindex='0']>li ")
    public List<WebElement> cabinTypeDropdownOption;

    public void chooseCabinOptions(String str){
        cabinTypeDropdown.click();
        for (WebElement element : cabinTypeDropdownOption) {
            if(element.getAttribute("aria-label").equals(str)){
                element.click();
                break;
            }
        }
    }

    @FindBy(css = "button[aria-label='Find flights']")
    public WebElement findFlightsButton;

    @FindBy(id = "DepartDate")
    public WebElement departDate;

    @FindBy(xpath = "(//div[@id='flightResults-content']//span)[2]/..")
    public WebElement resultOfDepartureValidation;
}