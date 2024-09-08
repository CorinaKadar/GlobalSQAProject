package tests;

import helperMethods.WindowMethods;
import objectData.RegistrationLoginObject;
import org.testng.annotations.Test;
import pages.GlobalSQAHomePage;
import pages.LoginPage;
import pages.RegisterPage;
import pages.UserHomePage;
import sharedData.SharedData;

public class RegistrationLoginTest extends SharedData {

    @Test
    public void testMethod() {
        GlobalSQAHomePage globalSQAHomePage = new GlobalSQAHomePage(getDriver());
        globalSQAHomePage.consentCookiesIfVisible();
        globalSQAHomePage.hoverOverTestersHubMenu();
        globalSQAHomePage.hoverOverAngularJSPPSSubmenu();
        globalSQAHomePage.interactWithRegistrationFormSubmenu();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.interactWithTheRegisterLink();

        // Scenario 1 -- Registration Form - Create new user
        RegistrationLoginObject testData = new RegistrationLoginObject("src/test/resources/testData/RegistrationLoginData.json");
        RegisterPage registerPage = new RegisterPage(getDriver());
        registerPage.registerNewUser(testData);
        loginPage.validateRegistrationSuccessfulMessage();
        WindowMethods windowMethods = new WindowMethods(getDriver());
        windowMethods.refreshPage();

        // Scenario 2 -- Login User
        loginPage.loginUser(testData.getUsernameValue(), testData.getPasswordValue());
        UserHomePage userHomePage = new UserHomePage(getDriver());
        userHomePage.validateUserLoginMessage(testData);

        // Scenario 3 -- Logout User
        userHomePage.logoutUser();
    }
}