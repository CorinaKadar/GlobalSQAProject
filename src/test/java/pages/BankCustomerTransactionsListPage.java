package pages;

import loggerUtility.LoggerUtility;
import objectData.BankCustomerObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class BankCustomerTransactionsListPage extends BasePage {

    public BankCustomerTransactionsListPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "start")
    private WebElement startDateElement;

    @FindBy(xpath = "//button[@class='btn logout' and contains(text(), 'Logout')]")
    private WebElement logoutButton;

    @FindBy(xpath = "//table[@class='table table-bordered table-striped']//tbody/tr")
    private List<WebElement> transactionsTableRowValues;

    @FindBy(xpath = "//table[@class='table table-bordered table-striped']//tbody")
    private WebElement transactionsTable;

    @FindBy(xpath = "//table[@class='table table-bordered table-striped']//tbody/tr/td[1]")
    private List<WebElement> columnDateTimeValues;

    @FindBy(xpath = "//table[@class='table table-bordered table-striped']//tbody/tr/td[2]")
    private List<WebElement> columnAmountValues;

    @FindBy(xpath = "//table[@class='table table-bordered table-striped']//tbody/tr/td[3]")
    private List<WebElement> columnTransactionTypeValues;


    public void filterTransactionsByStartDateField(String formattedCurrentDateForFilter) {
        elementMethods.waitForPageToLoad();
        LoggerUtility.info("Starting to filter the transactions by date and time: " + formattedCurrentDateForFilter);
        try {
            //elementMethods.waitForElementToBeClickable(startDateElement);
            elementMethods.fluentWait(startDateElement);
            LoggerUtility.info("'Start Date' field is clickable.");
            elementMethods.clearField(startDateElement);
            LoggerUtility.info("'Start Date' field is clear.");
            elementMethods.insertValue(startDateElement, formattedCurrentDateForFilter);
            LoggerUtility.info("The start date: " + formattedCurrentDateForFilter + " is successfully inserted into the 'Start Date' field.");
            elementMethods.pressElement(startDateElement, Keys.ENTER);
            LoggerUtility.info("Successfully pressed 'Enter' to submit the start date value.");
            elementMethods.waitForPageToLoad();
            //String extractedXPath = elementMethods.getFindByAnnotationValue(this, "transactionsTable");
            //elementMethods.waitForVisibilityOfAllElementsLocatedBy(extractedXPath);
            LoggerUtility.info("The Transactions tables is fully loaded");
            LoggerUtility.info("Successfully filtered the transactions by the Start Date field: " + formattedCurrentDateForFilter);
            //LoggerUtility.info("Number of rows retrieved: " + transactionsTableRowValues.size());
        } catch (Exception e) {
            LoggerUtility.error("An error occurred while filtering transactions by the date and time: " + formattedCurrentDateForFilter + ". Error: " + e.getMessage());
            throw e;
        }
    }

    public void logoutCustomer() {
        LoggerUtility.info("Starting the logout process for the customer.");
        try {
            elementMethods.waitForElementToBeClickable(logoutButton);
            LoggerUtility.info("Logout button is clickable.");
            elementMethods.clickElement(logoutButton);
            LoggerUtility.info("Successfully clicked on the 'Logout' button.");
            LoggerUtility.info("The customer is successfully logged out.");
        } catch (Exception e) {
            LoggerUtility.error("An error occurred during the logout process. Error: " + e.getMessage());
            throw e;
        }
    }

    // Method to validate each value from each table row.
    // This method takes a list of BankCustomerObject objects, which represent the expected values for each row in the Transactions table.
    public void validateTransactionTableRows(List<BankCustomerObject> expectedValues) {
        elementMethods.waitForPageToLoad();
        elementMethods.fluentWait(transactionsTable);
        //elementMethods.waitForElementToBeVisible(transactionsTable);
        LoggerUtility.info("Starting the validation process for the Transactions table rows.");
        //String extractedXPath = elementMethods.getFindByAnnotationValue(this, "transactionsTableRowValues");
        //LoggerUtility.info("Successfully extracted the required xpath string value.");
        //elementMethods.waitForPresenceOfAllElementsLocatedBy(extractedXPath);
        //LoggerUtility.info("All items in the Transactions table are displayed.");
        //transactionsTableRowValues = elementMethods.refreshTransactionTableRowValues(extractedXPath);
        //LoggerUtility.info("Transaction table is refreshed. Number of rows retrieved: " + transactionsTableRowValues.size());
        try {
            for (Integer i = 0; i < transactionsTableRowValues.size(); i++) {
                String actualDateTime = columnDateTimeValues.get(i).getText().trim();
                String actualAmount = columnAmountValues.get(i).getText().trim();
                String actualTransactionType = columnTransactionTypeValues.get(i).getText().trim();

                String actualFormattedDate = elementMethods.formatActualTransactionDate(actualDateTime); // This formatting ensures that the date values are in a consistent format for accurate comparison.
                LoggerUtility.info("Successfully extracted the Actual values from row " + (i + 1) + " - Date-Time: " + actualFormattedDate + ", Amount: " + actualAmount + ", Transaction Type: " + actualTransactionType);

                // Get the expected values from the BankCustomerObject object
                BankCustomerObject expectedTransaction = expectedValues.get(i);
                String expectedFormattedDate = elementMethods.formatExpectedTransactionDate(expectedTransaction.getDateTime()); // This formatting ensures that the date values are in a consistent format for accurate comparison.
                LoggerUtility.info("Successfully extracted the Expected values for row " + (i + 1) + " - Date-Time: " + expectedFormattedDate + ", Amount: " + expectedTransaction.getAmount() + ", Transaction Type: " + expectedTransaction.getTransactionType());

                // Assert that each value matches the expected value
                Assert.assertEquals(expectedFormattedDate, actualFormattedDate);
                Assert.assertEquals(expectedTransaction.getAmount(), actualAmount);
                Assert.assertEquals(expectedTransaction.getTransactionType(), actualTransactionType);
                LoggerUtility.info("The Transactions table values from row " + (i + 1) + " are successfully validated.");
            }
            LoggerUtility.info("The validation process for the Transactions table rows is completed.");
        } catch (Exception e) {
            LoggerUtility.error("An error occurred during the validation of the Transactions table rows: " + e.getMessage());
            throw e;
        }
    }
}