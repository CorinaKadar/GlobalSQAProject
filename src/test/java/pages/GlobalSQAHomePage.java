package pages;

import loggerUtility.LoggerUtility;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GlobalSQAHomePage extends BasePage {

    public GlobalSQAHomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//p[@class='fc-button-label' and contains(text(), 'Consent')]")
    private WebElement consentButton;

    @FindBy(xpath = "//a[@class='no_border' and contains(text(), 'Tester’s Hub')]")
    private WebElement testersHubMenu;

    @FindBy(xpath = "//a[span[contains(text(), 'AngularJS Protractor Practice Site')]]")
    private WebElement angularJSPPSSubmenu;

    @FindBy(xpath = "//a[span[contains(text(), 'Banking Project')]]")
    private WebElement bankingProjectSubmenu;

    @FindBy(xpath = "//a[span[contains(text(), 'Registration Form')]]")
    private WebElement registrationFormSubmenu;

    public void consentCookies() {
        WebElement consentButtonOptional = null;
        try {
            consentButtonOptional = elementMethods.waitForCookie(consentButton);
        } catch (TimeoutException e) {
            LoggerUtility.info("No 'Consent' button was shown on the page.");
        }
        try {
            if (consentButtonOptional != null && consentButtonOptional.isDisplayed()) {
                LoggerUtility.info("'Consent' button is visible.");
                elementMethods.waitForElementToBeClickable(consentButtonOptional);
                elementMethods.clickJSElement(consentButtonOptional);
                LoggerUtility.info("Successfully clicked on the 'Consent' button.");
            }
        } catch (Exception e) {
            LoggerUtility.error("An error occurred while consenting to cookies: " + e.getMessage());
            throw e;
        }
    }

    public void hoverOverTestersHubMenu() {
        try {
            elementMethods.waitForElementToBeVisible(testersHubMenu);
            LoggerUtility.info("'Tester's Hub' menu is visible.");
            elementMethods.hoverOverElement(testersHubMenu);
            LoggerUtility.info("Successfully hovered over the 'Tester's Hub' menu.");
        } catch (Exception e) {
            LoggerUtility.error("An error occurred while hovering over the 'Tester's Hub' menu: " + e.getMessage());
            throw e;
        }
    }

    public void hoverOverAngularJSPPSSubmenu() {
        try {
            elementMethods.waitForElementToBeVisible(angularJSPPSSubmenu);
            LoggerUtility.info("'AngularJS Protractor Practice Site' submenu is visible.");
            elementMethods.hoverOverElement(angularJSPPSSubmenu);
            LoggerUtility.info("Successfully hovered over the 'AngularJS Protractor Practice Site' submenu.");
        } catch (Exception e) {
            LoggerUtility.error("An error occurred while hovering over the 'AngularJS Protractor Practice Site' submenu: " + e.getMessage());
            throw e;
        }
    }

    public void interactWithBankingProjectSubmenu() {
        try {
            elementMethods.waitForElementToBeClickable(bankingProjectSubmenu);
            LoggerUtility.info("'Banking Project' submenu is clickable.");
            elementMethods.clickElement(bankingProjectSubmenu);
            LoggerUtility.info("Successfully clicked on the 'Banking Project' submenu.");
        } catch (Exception e) {
            LoggerUtility.error("An error occurred while interacting with the 'Banking Project' submenu: " + e.getMessage());
            throw e;
        }
    }

    public void interactWithRegistrationFormSubmenu() {
        try {
            elementMethods.waitForElementToBeClickable(registrationFormSubmenu);
            LoggerUtility.info("'Registration Form' submenu is clickable.");
            elementMethods.clickElement(registrationFormSubmenu);
            LoggerUtility.info("Successfully clicked on the 'Registration Form' submenu.");
        } catch (Exception e) {
            LoggerUtility.error("An error occurred while interacting with the 'Registration Form' submenu: " + e.getMessage());
            throw e;
        }
    }
}