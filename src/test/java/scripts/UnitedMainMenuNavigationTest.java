package scripts;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.WindowHandler;

import java.util.ArrayList;
import java.util.List;

public class UnitedMainMenuNavigationTest extends UnitedBase {

    /**
     * Test Case 1: Validate "Main menu" navigation items
     * Given user is on "https://www.united.com/en/us"
     * Then user should see “Main menu” navigation items
     * |BOOK
     * |MY TRIPS
     * |TRAVEL INFO
     * |MILEAGEPLUS® PROGRAM
     * |DEALS
     * |HELP
     */

    @Test(priority = 1, description = "Validate \"Main menu\" navigation items")
    public void validateMainMenuNavigationItems() {

        String[] expectedText = {"BOOK", "MY TRIPS", "TRAVEL INFO", "MILEAGEPLUS® PROGRAM", "DEALS", "HELP"};
        for (int i = 0; i < expectedText.length; i++) {
            //With the help of this loop we can validate that particular WebElement is displayed, and the text is the same with expected
            Assert.assertEquals(unitedBasePage.mainMenuNavigationBar.get(i).getText(), expectedText[i]);
        }
    }

    /**
     * Test Case 2: Validate "Book travel menu" navigation items
     * Given user is on "https://www.united.com/en/us"
     * Then user should see "Book travel menu" navigation items
     * |Book
     * |Flight Status
     * |Check-in
     * |My trips
     */

    @Test(priority = 2, description = "Validate \"Book travel menu\" navigation items")
    public void validateBookTravelMenu() {
        String[] expectedText = {"Book", "Flight status", "Check-in", "My trips"};
        for (int i = 0; i < expectedText.length; i++) {
            //With the help of this loop we can validate that particular WebElement is displayed, and the text is the same with expected
            Assert.assertEquals(unitedBasePage.bookTravelMenu.get(i).getText(), expectedText[i]);
        }
    }

    /**
     * Test Case 3: Validate "Round-trip" and "One-way" radio buttons
     * Given user is on "https://www.united.com/en/us"
     * Then validate "Roundtrip" radio button is displayed, is enabled and is selected
     * And validate "One-way" radio button is displayed, is enabled but is not selected
     * When user clicks on "One-way" radio button
     * Then validate "One-way" radio button is selected while "Roundtrip" radio button is
     * deselected
     */

    @Test(priority = 3, description = "Validate \"Round-trip\" and \"One-way\" radio buttons")
    public void validateRadioButtons() {
        for (int i = 0; i < unitedBasePage.flightTypesInputs.size(); i++) {
            Assert.assertTrue(unitedBasePage.flightTypesLabels.get(i).isDisplayed());
            Assert.assertTrue(unitedBasePage.flightTypesLabels.get(i).isEnabled());
            if (i == 0) Assert.assertTrue(unitedBasePage.flightTypesInputs.get(i).isSelected());
            else Assert.assertFalse(unitedBasePage.flightTypesInputs.get(i).isSelected());
        }

        unitedBasePage.getButton(1).click();
        Assert.assertFalse(unitedBasePage.flightTypesInputs.get(0).isSelected());
        Assert.assertTrue(unitedBasePage.flightTypesInputs.get(1).isSelected());
    }

    /**
     * Test Case 4: Validate "Book with miles" and "Flexible dates" checkboxes
     * Given user is on "https://www.united.com/en/us"
     * Then validate "Book with miles" checkbox is displayed, is enabled but is not selected
     * And validate "Flexible dates" checkbox is displayed, is enabled but is not selected
     * When user clicks both checkboxes
     * Then validate both checkboxes are selected
     * When user clicks on both selected checkboxes again
     * Then validate both checkboxes are deselected
     */

    @Test(priority = 4, description = "Validate \"Book with miles\" and \"Flexible dates\" checkboxes")
    public void validateCheckboxes() {
        List<WebElement> checkboxesInputs = new ArrayList<>();
        checkboxesInputs.add(unitedBasePage.milesInput);
        checkboxesInputs.add(unitedBasePage.flexibleDatesInput);

        List<WebElement> checkboxesLabels = new ArrayList<>();
        checkboxesLabels.add(unitedBasePage.milesLabel);
        checkboxesLabels.add(unitedBasePage.flexibleDatesLabel);

        for (WebElement checkbox : checkboxesLabels) {
            Assert.assertTrue(checkbox.isDisplayed());
            Assert.assertTrue(checkbox.isEnabled());
            Assert.assertFalse(checkbox.isSelected());
        }

        for (int i = 0; i < checkboxesLabels.size(); i++) {
            checkboxesLabels.get(i).click();
            Assert.assertTrue(checkboxesInputs.get(i).isSelected());
        }

        for (int i = 0; i < checkboxesLabels.size(); i++) {
            checkboxesLabels.get(i).click();
            Assert.assertFalse(checkboxesInputs.get(i).isSelected());
        }
    }

    /**
     * Test Case 5: Validate One-way ticket search results "from Chicago, IL, US (ORD) to
     * Miami, FL, US (MIA)”
     * Given user is on "https://www.united.com/en/us"
     * When user selects "One-way" ticket radio button
     * And user enters "Chicago, IL, US (ORD)" to from input box
     * And user enters "Miami, FL, US (MIA)" to to input box
     * And user selects "Feb 28" to the dates input box
     * And user selects "2 Adults" from travelers selector
     * And user selects "Business or First" from cabin dropdown
     * And user clicks on "Find Flights" button
     * Then validate departure equals to "DEPART ON: February 28"
     */

    @Test(priority = 5, description = "Validate One-way ticket search results \"from Chicago, IL, US (ORD) to Miami, FL, US (MIA)”")
    public void validate() {
        unitedBasePage.getButton(1).click();
        unitedBasePage.departurePlace.clear();
        unitedBasePage.departurePlace.sendKeys("Chicago, IL, US (ORD)");
        unitedBasePage.departurePlace.click();
        unitedBasePage.arrivalPlace.sendKeys("Miami, FL, US (MIA)");
        unitedBasePage.arrivalPlace.click();
        unitedBasePage.departDate.clear();
        unitedBasePage.departDate.sendKeys("February 28");
        unitedBasePage.departDate.click();
        unitedBasePage.travelersOption.click();
        unitedBasePage.addAdult.click();
        unitedBasePage.chooseCabinOptions("Business or First");
        unitedBasePage.findFlightsButton.click();
        WindowHandler.switchToChildWindow();
        Assert.assertEquals(unitedBasePage.resultOfDepartureValidation.getText(), "DEPART ON: February 28");
    }
}