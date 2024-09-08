package tests;

import objectData.BankManagerObject;
import org.testng.annotations.Test;
import pages.*;
import sharedData.SharedData;

public class BankManagerTest extends SharedData {

    @Test
    public void testMethod() {
        GlobalSQAHomePage globalSQAHomePage = new GlobalSQAHomePage(getDriver());
        globalSQAHomePage.consentCookies();
        globalSQAHomePage.hoverOverTestersHubMenu();
        globalSQAHomePage.hoverOverAngularJSPPSSubmenu();
        globalSQAHomePage.interactWithBankingProjectSubmenu();
        BankHomePage bankHomePage = new BankHomePage(getDriver());
        bankHomePage.interactWithBankManagerLoginButton();

        //Scenario 1 -- Add Customer
        BankManagerObject testData = new BankManagerObject("src/test/resources/testData/BankManagerData.json");
        BankManagerHomePage bankManagerHomePage = new BankManagerHomePage(getDriver());
        bankManagerHomePage.interactWithAddCustomerTab();
        BankManagerAddCustomerPage bankManagerAddCustomerPage = new BankManagerAddCustomerPage(getDriver());
        String customerFullName = bankManagerAddCustomerPage.addCustomer(testData.getFirstName(), testData.getLastName(), testData.getPostCode());
        bankManagerAddCustomerPage.validateAddCustomerSuccessfulMessage();

        // Scenario 2 -- Open Account
        bankManagerHomePage.interactWithOpenAccountTab();
        BankManagerOpenAccountPage bankManagerOpenAccountPage = new BankManagerOpenAccountPage(getDriver());
        String resultedAccountNumber = bankManagerOpenAccountPage.openAccount(customerFullName, testData.getCurrency());
        bankManagerOpenAccountPage.validateOpenAccountSuccessfulMessage();

        // Scenario 3 -- Customers List
        bankManagerHomePage.interactWithCustomersTab();
        BankManagerCustomersListPage bankManagerCustomersListPage = new BankManagerCustomersListPage(getDriver());
        bankManagerCustomersListPage.searchCustomerByAccountNumber(resultedAccountNumber);
        BankManagerObject expectedCustomer = new BankManagerObject(testData.getFirstName(), testData.getLastName(), testData.getPostCode(), resultedAccountNumber);
        bankManagerCustomersListPage.validateCustomerTableRow(expectedCustomer);

        // Scenario 4 -- Delete Customer
        bankManagerCustomersListPage.deleteCustomer();
        bankManagerCustomersListPage.interactWithHomeButton();
    }
}