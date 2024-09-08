package pages;

import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class BankCustomerHomePage extends BasePage {

    public BankCustomerHomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@ng-class='btnClass2']")
    private WebElement depositTabElement;

    @FindBy(xpath = "//input[@placeholder='amount']")
    private WebElement amountToBeDepositedElement;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement depositButton;

    @FindBy(xpath = "//button[@ng-class='btnClass3']")
    private WebElement withdrawlTabElement;

    @FindBy(xpath = "//input[@placeholder='amount']")
    private WebElement amountToBeWithdrawnElement;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement withdrawButton;

    @FindBy(xpath = "//button[@ng-class='btnClass1']")
    private WebElement transactionsTabElement;

    @FindBy(xpath = "//div[@class='borderM box padT20 ng-scope']//strong[contains(., 'Welcome') and contains(., '!!')]")
    private WebElement customerWelcomeMessageElement;

    @FindBy(xpath = "//span[@class='error ng-binding']")
    private WebElement depositSuccessfulMessageElement;

    @FindBy(xpath = "//span[@class='error ng-binding']")
    private WebElement withdrawnSuccessfulMessageElement;

    public void depositAmount(String amountToBeDepositedValue) {
        LoggerUtility.info("Starting the deposit process.");
        try {
            elementMethods.waitForElementToBeClickable(depositTabElement);
            LoggerUtility.info("'Deposit' tab is clickable.");
            elementMethods.clickElement(depositTabElement);
            LoggerUtility.info("Successfully clicked on the 'Deposit' tab.");
            elementMethods.insertValue(amountToBeDepositedElement, amountToBeDepositedValue);
            LoggerUtility.info("The amount: " + amountToBeDepositedValue + " is successfully inserted in the 'Amount to be Deposited' field.");
            elementMethods.clickElement(depositButton);
            LoggerUtility.info("Successfully clicked on the 'Deposit' button.");
            LoggerUtility.info("The deposit process is completed. The amount: " + amountToBeDepositedValue + " is successfully deposited.");
        } catch (Exception e) {
            LoggerUtility.error("Error occurred during the deposit process: " + e.getMessage());
            throw e;
        }
    }

    public void withdrawAmount(String amountToBeWithdrawnValue) {
        LoggerUtility.info("Starting the withdrawal process.");
        try {
            elementMethods.waitForElementToBeClickable(withdrawlTabElement);
            LoggerUtility.info("'Withdrawl' tab is clickable.");
            elementMethods.clickElement(withdrawlTabElement);
            LoggerUtility.info("Successfully clicked on the 'Withdrawl' tab.");
            elementMethods.insertValue(amountToBeWithdrawnElement, amountToBeWithdrawnValue);
            LoggerUtility.info("The amount: " + amountToBeWithdrawnValue + " is successfully inserted in the 'Amount to be Withdrawn' field.");
            elementMethods.clickElement(withdrawButton);
            LoggerUtility.info("Successfully clicked on the 'Withdraw' button.");
            LoggerUtility.info("The withdrawal process is completed. The amount: " + amountToBeWithdrawnValue + " is successfully withdrawn.");
        } catch (Exception e) {
            LoggerUtility.error("Error occurred during the withdrawal process: " + e.getMessage());
            throw e;
        }
    }

    public void interactWithTheTransactionsTab() {
        try {
            elementMethods.waitForElementToBeClickable(transactionsTabElement);
            LoggerUtility.info("'Transactions' tab is clickable.");
            elementMethods.clickElement(transactionsTabElement);
            LoggerUtility.info("Successfully clicked on the 'Transactions' tab.");
        } catch (Exception e) {
            LoggerUtility.error("An error occurred while interacting with the 'Transactions' tab: " + e.getMessage());
            throw e;
        }
    }

    public void validateCustomerLoginMessage(String customerFullNameValue) {
        try {
            elementMethods.waitForElementToBeVisible(customerWelcomeMessageElement);
            LoggerUtility.info("'Welcome " + customerFullNameValue + " !!' message is visible.");
            Assert.assertEquals(elementMethods.getText(customerWelcomeMessageElement), "Welcome " + customerFullNameValue + " !!");
            LoggerUtility.info("'Welcome " + customerFullNameValue + " !!' message is successfully validated.");
        } catch (Exception e) {
            LoggerUtility.error("An error occurred while validating the customer login message: " + e.getMessage());
            throw e;
        }
    }

    public void validateDepositSuccessfulMessage() {
        try {
            elementMethods.waitForElementToBeVisible(depositSuccessfulMessageElement);
            LoggerUtility.info("'Deposit Successful' message is visible.");
            Assert.assertEquals(elementMethods.getText(depositSuccessfulMessageElement), "Deposit Successful");
            LoggerUtility.info("'Deposit Successful' message is successfully validated.");
        } catch (Exception e) {
            LoggerUtility.error("An error occurred while validating the 'Deposit Successful' message: " + e.getMessage());
            throw e;
        }
    }

    public void validateWithdrawnSuccessfulMessage() {
        try {
            elementMethods.waitForElementToBeVisible(withdrawnSuccessfulMessageElement);
            LoggerUtility.info("'Transaction successful' message is visible.");
            Assert.assertEquals(elementMethods.getText(withdrawnSuccessfulMessageElement), "Transaction successful");
            LoggerUtility.info("'Transaction successful' message is successfully validated.");
        } catch (Exception e) {
            LoggerUtility.error("An error occurred while validating the 'Transaction successful' message: " + e.getMessage());
            throw e;
        }
    }
}