package pages;

import loggerUtility.LoggerUtility;
import objectData.BankManagerObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class BankManagerCustomersListPage extends BasePage {

    public BankManagerCustomersListPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Search Customer']")
    private WebElement searchCustomerElement;

    @FindBy(xpath = "//button[@ng-click='deleteCust(cust)']")
    private WebElement deleteButton;

    @FindBy(xpath = "//button[@class='btn home' and contains(text(), 'Home')]")
    private WebElement homeButton;

    @FindBy(xpath = "//table[@class='table table-bordered table-striped']//tbody/tr")
    private List<WebElement> customersTableRowValues;

    @FindBy(xpath = "//table[@class='table table-bordered table-striped']//tbody/tr/td[1]")
    private List<WebElement> columnFirstNameValues;

    @FindBy(xpath = "//table[@class='table table-bordered table-striped']//tbody/tr/td[2]")
    private List<WebElement> columnLastNameValues;

    @FindBy(xpath = "//table[@class='table table-bordered table-striped']//tbody/tr/td[3]")
    private List<WebElement> columnPostCodeValues;

    @FindBy(xpath = "//table[@class='table table-bordered table-striped']//tbody/tr/td[4]")
    private List<WebElement> columnAccountNumberValues;

    public void searchCustomerByAccountNumber(String resultedAccountNumber) {
        LoggerUtility.info("Starting the process to search for a customer by account number.");
        try {
            elementMethods.waitForElementToBeClickable(searchCustomerElement);
            LoggerUtility.info("'Search Customer' field is clickable.");
            elementMethods.insertValue(searchCustomerElement, resultedAccountNumber);
            LoggerUtility.info("Account number: " + resultedAccountNumber + " is successfully inserted into the search field.");
        } catch (Exception e) {
            LoggerUtility.error("An error occurred while searching for the customer by account number: " + e.getMessage());
            throw e;
        }
    }

    public void deleteCustomer() {
        LoggerUtility.info("Starting the process to delete a customer.");
        try {
            elementMethods.waitForElementToBeClickable(deleteButton);
            LoggerUtility.info("'Delete' button is clickable.");
            elementMethods.clickElement(deleteButton);
            LoggerUtility.info("Successfully clicked on the 'Delete' button.");
            LoggerUtility.info("The customer is successfully deleted.");
        } catch (Exception e) {
            LoggerUtility.error("An error occurred while deleting the customer: " + e.getMessage());
            throw e;
        }
    }

    public void interactWithHomeButton() {
        try {
            elementMethods.waitForElementToBeClickable(homeButton);
            LoggerUtility.info("'Home' button is clickable.");
            elementMethods.clickElement(homeButton);
            LoggerUtility.info("Successfully clicked on the 'Home' button.");
        } catch (Exception e) {
            LoggerUtility.error("An error occurred while interacting with the 'Home' button: " + e.getMessage());
            throw e;
        }
    }

    // The method iterates through each row of a table displayed on a web page, extracts the data from each cell in the row, and compares it with the expected values stored in an instance of CustomersTableModel.
    public void validateCustomerTableRow(BankManagerObject expectedValues) {
        LoggerUtility.info("Starting the validation process for the Customers table rows.");
        try {
            for (Integer i = 0; i < customersTableRowValues.size(); i++) {
                String actualFirstName = columnFirstNameValues.get(i).getText().trim();
                String actualLastName = columnLastNameValues.get(i).getText().trim();
                String actualPostCode = columnPostCodeValues.get(i).getText().trim();
                String actualAccountNumber = columnAccountNumberValues.get(i).getText().trim();
                LoggerUtility.info("Successfully extracted the actual values - First Name: " + actualFirstName + " - Last Name: " + actualLastName + " - Post Code: " + actualPostCode + " - Account Number: " + actualAccountNumber);
                LoggerUtility.info("Successfully extracted the expected values - First Name: " + expectedValues.getFirstName() + " - Last Name: " + expectedValues.getLastName() + " - Post Code: " + expectedValues.getPostCode() + " - Account Number: " + expectedValues.getAccountNumber());

                Assert.assertEquals(expectedValues.getFirstName(), actualFirstName);
                Assert.assertEquals(expectedValues.getLastName(), actualLastName);
                Assert.assertEquals(expectedValues.getPostCode(), actualPostCode);
                Assert.assertEquals(expectedValues.getAccountNumber(), actualAccountNumber);
                LoggerUtility.info("Customers table values are successfully validated.");
                LoggerUtility.info("The validation process for the Customers table rows is completed.");
            }
        } catch (Exception e) {
            LoggerUtility.error("An error occurred while validating the Customers table row: " + e.getMessage());
            throw e;
        }
    }
}