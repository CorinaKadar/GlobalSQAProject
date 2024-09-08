package pages;

import loggerUtility.LoggerUtility;
import objectData.AlertMessageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class BankManagerOpenAccountPage extends BasePage {

    public BankManagerOpenAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "userSelect")
    private WebElement customerElement;

    @FindBy(id = "currency")
    private WebElement currencyElement;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement processButton;

    public String openAccount(String customerFullName, String currencyValue) {
        LoggerUtility.info("Starting the process to open an account.");
        try {
            elementMethods.clickElement(customerElement);
            LoggerUtility.info("Successfully clicked on the 'Customer' field.");
            elementMethods.selectByValue(customerElement, customerFullName);
            LoggerUtility.info(customerFullName + " is selected.");
            elementMethods.clickElement(currencyElement);
            LoggerUtility.info("Successfully clicked on the 'Currency' field.");
            elementMethods.selectByValue(currencyElement, currencyValue);
            LoggerUtility.info(currencyValue + " is selected.");
            elementMethods.clickElement(processButton);
            LoggerUtility.info("Successfully clicked on the 'Process' button.");
            String resultedAccountNumber = alertMethods.extractNumberValueFromAlertMessage();
            LoggerUtility.info("The account for customer: " + customerFullName + " with currency: " + currencyValue + " is successfully opened and the account number is: " + resultedAccountNumber);
            return resultedAccountNumber;
        } catch (Exception e) {
            LoggerUtility.error("An error occurred while opening an account for customer: " + customerFullName + " with currency: " + currencyValue + ". Error: " + e.getMessage());
            throw e;
        }
    }

    public void validateOpenAccountSuccessfulMessage() {
        LoggerUtility.info("Starting the validation process for the account creation success message.");
        try {
            // The method calls extractPartsFromAlertMessage() to extract both alertText and alertNumberValue from the alert message.
            AlertMessageObject alertMessageObject = alertMethods.extractPartsFromAlertMessage();
            LoggerUtility.info("Extracted the alert message parts - Alert Number: " + alertMessageObject.alertNumberValue + " - Alert Text: " + alertMessageObject.alertText);
            String accountNumber = alertMessageObject.alertNumberValue;
            String alertMessageText = alertMessageObject.alertText;
            String actualAlertMessage = alertMessageText + ":" + accountNumber;
            // It reconstructs the alert message using the parsed parts (alertText and alertNumberValue).
            LoggerUtility.info("Constructed the actual alert message: " + actualAlertMessage);
            Assert.assertEquals(actualAlertMessage, "Account created successfully with account Number:" + accountNumber);
            LoggerUtility.info("The alert message is successfully validated.");
            alertMethods.acceptAlert();
            LoggerUtility.info("The alert is successfully accepted.");
        } catch (Exception e) {
            LoggerUtility.error("An error occurred while validating the account creation success message: " + e.getMessage());
            throw e;
        }
    }
}