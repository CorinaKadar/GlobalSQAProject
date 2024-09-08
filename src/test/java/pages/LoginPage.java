package pages;

import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@class='btn btn-link' and contains(text(), 'Register')]")
    private WebElement registerLink;

    @FindBy(id = "username")
    private WebElement loginUsernameElement;

    @FindBy(id = "password")
    private WebElement loginPasswordElement;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@class='ng-binding ng-scope alert alert-success' and contains(text(), 'Registration successful')]")
    private WebElement registrationSuccessfulElement;

    public void interactWithTheRegisterLink() {
        try {
            elementMethods.waitForElementToBeClickable(registerLink);
            LoggerUtility.info("'Register' link is clickable.");
            elementMethods.clickElement(registerLink);
            LoggerUtility.info("Successfully clicked on the 'Register' link.");
        } catch (Exception e) {
            LoggerUtility.error("An error occurred while interacting with the 'Register' link: " + e.getMessage());
            throw e;
        }
    }

    public void loginUser(String usernameValue, String passwordValue) {
        LoggerUtility.info("Starting the login process for Username: " + usernameValue);
        try {
            elementMethods.waitForElementToBeClickable(loginUsernameElement);
            LoggerUtility.info("'Username' field is clickable.");
            elementMethods.insertValue(loginUsernameElement, usernameValue);
            LoggerUtility.info("'" + usernameValue + "' is successfully inserted in the 'Username' field.");
            elementMethods.insertValue(loginPasswordElement, passwordValue);
            LoggerUtility.info("'" + passwordValue + "' is successfully inserted in the 'Password' field.");
            elementMethods.clickElement(loginButton);
            LoggerUtility.info("Successfully clicked on the 'Login' button.");
            LoggerUtility.info("'" + usernameValue + "' is successfully logged in.");
        } catch (Exception e) {
            LoggerUtility.error("An error occurred during login process with username: " + usernameValue + ". Error: " + e.getMessage());
            throw e;
        }
    }

    public void validateRegistrationSuccessfulMessage() {
        try {
            elementMethods.waitForElementToBeVisible(registrationSuccessfulElement);
            LoggerUtility.info("'Registration successful' message is visible.");
            Assert.assertEquals(elementMethods.getText(registrationSuccessfulElement), "Registration successful");
            LoggerUtility.info("'Registration successful' message is successfully validated.");
        } catch (Exception e) {
            LoggerUtility.error("An error occurred while validating the 'Registration successful' message: " + e.getMessage());
            throw e;
        }
    }
}