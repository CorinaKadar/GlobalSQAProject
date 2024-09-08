package pages;

import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BankHomePage extends BasePage {

    public BankHomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@class='btn btn-primary btn-lg' and contains(text(), 'Bank Manager Login')]")
    private WebElement bankManagerLoginButton;

    @FindBy(xpath = "//button[@class='btn btn-primary btn-lg' and contains(text(), 'Customer Login')]")
    private WebElement customerLoginButton;

    public void interactWithBankManagerLoginButton() {
        try {
            elementMethods.waitForElementToBeClickable(bankManagerLoginButton);
            LoggerUtility.info("'Bank Manager Login' button is clickable.");
            elementMethods.clickElement(bankManagerLoginButton);
            LoggerUtility.info("Successfully clicked on the 'Bank Manager Login' button.");
        } catch (Exception e) {
            LoggerUtility.error("An error occurred while interacting with the 'Bank Manager Login' button: " + e.getMessage());
            throw e;
        }
    }

    public void interactWithCustomerLoginButton() {
        try {
            elementMethods.waitForElementToBeClickable(customerLoginButton);
            LoggerUtility.info("'Customer Login' button is clickable.");
            elementMethods.clickElement(customerLoginButton);
            LoggerUtility.info("Successfully clicked on the 'Customer Login' button.");
        } catch (Exception e) {
            LoggerUtility.error("An error occurred while interacting with the 'Customer Login' button: " + e.getMessage());
            throw e;
        }
    }
}