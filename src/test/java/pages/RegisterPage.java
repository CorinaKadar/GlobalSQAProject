package pages;

import loggerUtility.LoggerUtility;
import objectData.RegistrationLoginObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "firstName")
    private WebElement firstNameElement;

    @FindBy(xpath = "//input[@name='lastName']")
    private WebElement lastNameElement;

    @FindBy(id = "username")
    private WebElement usernameElement;

    @FindBy(id = "password")
    private WebElement passwordElement;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement registerButton;

    public void registerNewUser(RegistrationLoginObject testData) {
        LoggerUtility.info("Starting the registration process for a new user.");
        try {
            elementMethods.waitForElementToBeClickable(firstNameElement);
            LoggerUtility.info("'First name' field is clickable.");
            elementMethods.insertValue(firstNameElement, testData.getFirstNameValue());
            LoggerUtility.info("'" + testData.getFirstNameValue() + "' is successfully inserted in the 'First name' field.");
            elementMethods.insertValue(lastNameElement, testData.getLastNameValue());
            LoggerUtility.info("'" + testData.getLastNameValue() + "' is successfully inserted in the 'Last name' field.");
            elementMethods.insertValue(usernameElement, testData.getUsernameValue());
            LoggerUtility.info("'" + testData.getUsernameValue() + "' is successfully inserted in the 'Username' field.");
            elementMethods.insertValue(passwordElement, testData.getPasswordValue());
            LoggerUtility.info("'" + testData.getPasswordValue() + "' is successfully inserted in the 'Password' field.");
            elementMethods.clickElement(registerButton);
            LoggerUtility.info("Successfully clicked on the 'Register' button.");
            LoggerUtility.info("A new user is successfully registered: " + testData.getFirstNameValue() + " " + testData.getLastNameValue() + " with Username: " + testData.getUsernameValue());
        } catch (Exception e) {
            LoggerUtility.error("An error occurred while registering a new user: " + e.getMessage());
            throw e;
        }
    }
}