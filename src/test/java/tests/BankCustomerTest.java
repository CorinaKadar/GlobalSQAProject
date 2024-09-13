package tests;

import helperMethods.ElementMethods;
import helperMethods.WindowMethods;
import objectData.BankCustomerObject;
import org.testng.annotations.Test;
import pages.*;
import sharedData.SharedData;

import java.util.Arrays;
import java.util.List;

public class BankCustomerTest extends SharedData {

    @Test
    public void testMethod() {
        ElementMethods elementMethods = new ElementMethods(getDriver());
        GlobalSQAHomePage globalSQAHomePage = new GlobalSQAHomePage(getDriver());
        globalSQAHomePage.consentCookiesIfVisible();
        globalSQAHomePage.hoverOverTestersHubMenu();
        globalSQAHomePage.hoverOverAngularJSPPSSubmenu();
        globalSQAHomePage.interactWithBankingProjectSubmenu();
        BankHomePage bankHomePage = new BankHomePage(getDriver());
        bankHomePage.interactWithCustomerLoginButton();

        // Scenario 1 -- Login
        BankCustomerObject testData = new BankCustomerObject("src/test/resources/testData/BankCustomerData.json");
        BankCustomerLoginPage bankCustomerLoginPage = new BankCustomerLoginPage(getDriver());
        bankCustomerLoginPage.loginCustomer(testData.getCustomerName());
        BankCustomerHomePage bankCustomerHomePage = new BankCustomerHomePage(getDriver());
        bankCustomerHomePage.validateCustomerLoginMessage(testData.getCustomerName());

        // Getting the current date
        String formattedCurrentDate = elementMethods.getCurrentDate();
        String formattedCurrentDateForFilter = elementMethods.getCurrentDateForFilter();

        // Scenario 2 -- Deposit
        bankCustomerHomePage.depositAmount(testData.getDepositedAmount());
        bankCustomerHomePage.validateDepositSuccessfulMessage();

        // Scenario 3 -- Withdrawal
        WindowMethods windowMethods = new WindowMethods(getDriver());
        windowMethods.refreshPage();
        bankCustomerHomePage.withdrawAmount(testData.getWithdrawnAmount());
        bankCustomerHomePage.validateWithdrawnSuccessfulMessage();

        // Scenario 4 -- Transactions
        bankCustomerHomePage.interactWithTheTransactionsTab();
        BankCustomerTransactionsListPage bankCustomerTransactionsListPage = new BankCustomerTransactionsListPage(getDriver());
        bankCustomerTransactionsListPage.filterTransactionsByStartDateField(formattedCurrentDateForFilter);
        List<BankCustomerObject> expectedRows = Arrays.asList(
                new BankCustomerObject(formattedCurrentDate, testData.getDepositedAmount(), "Credit"),
                new BankCustomerObject(formattedCurrentDate, testData.getWithdrawnAmount(), "Debit"));
        bankCustomerTransactionsListPage.validateTransactionTableRows(expectedRows);

        // Scenario 5 -- Logout
        bankCustomerTransactionsListPage.logoutCustomer();
    }
}