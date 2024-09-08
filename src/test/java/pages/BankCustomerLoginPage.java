package pages;

import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BankCustomerLoginPage extends BasePage {

    public BankCustomerLoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "userSelect")
    private WebElement customerFullNameElement;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    public void loginCustomer(String customerFullNameValue) {
        LoggerUtility.info("Starting the login process for customer: " + customerFullNameValue);
        try {
            elementMethods.waitForElementToBeClickable(customerFullNameElement);
            LoggerUtility.info("'"+ customerFullNameValue + "' option is clickable.");
            elementMethods.clickElement(customerFullNameElement);
            LoggerUtility.info("Successfully clicked on the '" + customerFullNameValue + "' option.");
            elementMethods.selectByValue(customerFullNameElement, customerFullNameValue);
            LoggerUtility.info("'"+ customerFullNameValue + "' option is successfully selected for the 'Your Name' field.");
            elementMethods.clickElement(loginButton);
            LoggerUtility.info("Successfully clicked on the 'Login' button.");
            LoggerUtility.info("Customer '" + customerFullNameValue + "' is successfully logged in.");
        } catch (Exception e) {
            LoggerUtility.error("An error occurred during the login process for customer: " + customerFullNameValue + ". Error: " + e.getMessage());
            throw e;
        }
    }
}
