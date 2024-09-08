package pages;

import loggerUtility.LoggerUtility;
import objectData.AlertMessageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class BankManagerAddCustomerPage extends BasePage {

    public BankManagerAddCustomerPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@placeholder='First Name']")
    private WebElement firstNameElement;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    private WebElement lastNameElement;

    @FindBy(xpath = "//input[@placeholder='Post Code']")
    private WebElement postCodeElement;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement addCustomerButton;

    public String addCustomer(String firstNameValue, String lastNameValue, String postCodeValue) {
        LoggerUtility.info("Starting the process to add a new customer.");
        try {
            elementMethods.insertValue(firstNameElement, firstNameValue);
            LoggerUtility.info("First Name: " + firstNameValue + " is successfully inserted in the 'First Name' field.");
            elementMethods.insertValue(lastNameElement, lastNameValue);
            LoggerUtility.info("Last Name: " + lastNameValue + " is successfully inserted in the 'Last Name' field.");
            elementMethods.insertValue(postCodeElement, postCodeValue);
            LoggerUtility.info("Post Code: " + postCodeValue + " is successfully inserted in the 'Post Code' field.");
            elementMethods.clickElement(addCustomerButton);
            LoggerUtility.info("Successfully clicked on the 'Add Customer' button.");
            LoggerUtility.info("A new customer is successfully added: " + firstNameValue + " " + lastNameValue);
            return firstNameValue + " " + lastNameValue; // customerFullName
        } catch (Exception e) {
            LoggerUtility.error("An error occurred while adding a customer: " + e.getMessage());
            throw e;
        }
    }

    public void validateAddCustomerSuccessfulMessage() {
        LoggerUtility.info("Starting the validation process for the add customer success message.");
        try {
            // The method calls extractPartsFromAlertMessage() to extract both alertText and alertNumberValue from the alert message.
            AlertMessageObject alertMessageObject = alertMethods.extractPartsFromAlertMessage();
            LoggerUtility.info("Extracted the alert message parts - Alert Number: " + alertMessageObject.alertNumberValue + " - Alert Text: " + alertMessageObject.alertText);
            String customerId = alertMessageObject.alertNumberValue;
            String alertMessageText = alertMessageObject.alertText;
            String actualAlertMessage = alertMessageText + " :" + customerId;
            // It reconstructs the alert message using the parsed parts (alertText and alertNumberValue).
            LoggerUtility.info("Constructed the actual alert message: " + actualAlertMessage);
            Assert.assertEquals(actualAlertMessage, "Customer added successfully with customer id :" + customerId);
            LoggerUtility.info("The alert message is successfully validated.");
            alertMethods.acceptAlert();
            LoggerUtility.info("The alert is successfully accepted.");
        } catch (Exception e) {
            LoggerUtility.error("An error occurred while validating the customer creation success message: " + e.getMessage());
            throw e;
        }
    }
}