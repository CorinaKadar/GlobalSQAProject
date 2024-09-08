package pages;

import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BankManagerHomePage extends BasePage {

    public BankManagerHomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@ng-class='btnClass1']")
    private WebElement addCustomerTab;

    @FindBy(xpath = "//button[@ng-class='btnClass2']")
    private WebElement openAccountTab;

    @FindBy(xpath = "//button[@ng-class='btnClass3']")
    private WebElement customersTab;

    public void interactWithAddCustomerTab() {
        try {
            elementMethods.waitForElementToBeClickable(addCustomerTab);
            LoggerUtility.info("'Add Customer' tab is clickable.");
            elementMethods.clickElement(addCustomerTab);
            LoggerUtility.info("Successfully clicked on the 'Add Customer' tab.");
        } catch (Exception e) {
            LoggerUtility.error("An error occurred while interacting with the 'Add Customer' tab: " + e.getMessage());
            throw e;
        }
    }

    public void interactWithOpenAccountTab() {
        try {
            elementMethods.waitForElementToBeClickable(openAccountTab);
            LoggerUtility.info("'Open Account' tab is clickable.");
            elementMethods.clickElement(openAccountTab);
            LoggerUtility.info("Successfully clicked on the 'Open Account' tab.");
        } catch (Exception e) {
            LoggerUtility.error("An error occurred while interacting with the 'Open Account' tab: " + e.getMessage());
            throw e;
        }
    }

    public void interactWithCustomersTab() {
        try {
            elementMethods.waitForElementToBeClickable(customersTab);
            LoggerUtility.info("'Customers' tab is clickable.");
            elementMethods.clickElement(customersTab);
            LoggerUtility.info("Successfully clicked on the 'Customers' tab.");
        } catch (Exception e) {
            LoggerUtility.error("An error occurred while interacting with the 'Customers' tab: " + e.getMessage());
            throw e;
        }
    }
}