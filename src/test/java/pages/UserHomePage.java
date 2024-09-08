package pages;

import loggerUtility.LoggerUtility;
import objectData.RegistrationLoginObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class UserHomePage extends BasePage {

    public UserHomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@class='btn btn-primary' and contains(text(), 'Logout')]")
    private WebElement logoutButton;

    @FindBy(xpath = "//h1[contains(text(), 'Hi ') and contains(text(), '!')]")
    private WebElement hiMessageElement;

    @FindBy(xpath = "//p[contains(text(), \"You're logged in!!\")]")
    private WebElement youAreLoggedInMessageElement;

    public void logoutUser() {
        LoggerUtility.info("Starting the logout process for the user.");
        try {
            elementMethods.waitForElementToBeClickable(logoutButton);
            LoggerUtility.info("'Logout' button is clickable.");
            elementMethods.clickElement(logoutButton);
            LoggerUtility.info("Successfully clicked on the 'Logout' button.");
            LoggerUtility.info("The user is successfully logged out.");
        } catch (Exception e) {
            LoggerUtility.error("An error occurred while attempting to log out: " + e.getMessage());
            throw e;
        }
    }

    public void validateUserLoginMessage(RegistrationLoginObject testData) {
        LoggerUtility.info("Starting the validation process for the user login success message.");
        try {
            elementMethods.waitForElementToBeVisible(hiMessageElement);
            LoggerUtility.info("'Hi " + testData.getFirstNameValue() + "!' message is visible.");
            Assert.assertEquals(elementMethods.getText(hiMessageElement), "Hi " + testData.getFirstNameValue() + "!");
            LoggerUtility.info("Actual message is successfully validated: " + elementMethods.getText(hiMessageElement));
            elementMethods.waitForElementToBeVisible(youAreLoggedInMessageElement);
            LoggerUtility.info("'You're logged in!!' message is visible.");
            Assert.assertEquals(elementMethods.getText(youAreLoggedInMessageElement), "You're logged in!!");
            LoggerUtility.info("Actual message is successfully validated: " + elementMethods.getText(youAreLoggedInMessageElement));
        } catch (Exception e) {
            LoggerUtility.error("An error occurred while validating user login success message: " + e.getMessage());
            throw e;
        }
    }
}