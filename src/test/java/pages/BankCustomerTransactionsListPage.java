package pages;

import loggerUtility.LoggerUtility;
import objectData.BankCustomerObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public void filterTransactionsByDate(String formattedCurrentDate) {
        LoggerUtility.info("Starting to filter the transactions by date: " + formattedCurrentDate);
        try {
            elementMethods.waitForElementToBeClickable(startDateElement);
            LoggerUtility.info("'Start Date' field is clickable.");
            elementMethods.insertValue(startDateElement, formattedCurrentDate);
            LoggerUtility.info("The date: " + formattedCurrentDate + " is successfully inserted into the 'Start Date' field.");
            elementMethods.pressElement(startDateElement, Keys.ENTER);
            LoggerUtility.info("Successfully pressed 'Enter' to submit the date.");
            LoggerUtility.info("Successfully filtered the transactions by the date: " + formattedCurrentDate);
        } catch (Exception e) {
            LoggerUtility.error("An error occurred while filtering transactions by the date: " + formattedCurrentDate + ". Error: " + e.getMessage());
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
        // The method loops through each row of the TransactionsTableRowValues, which is a list of WebElement objects representing the rows of the Transactions table on the webpage.
        LoggerUtility.info("Starting the validation process for the Transactions table rows.");
        try {
            for (Integer i = 0; i < transactionsTableRowValues.size(); i++) {
                WebElement currentRow = transactionsTableRowValues.get(i);
                // Extracts the individual cells (<td> elements) from the current row using findElements.
                // It retrieves the text from each cell and trims any extra spaces: "actualDateTime" from the first cell (index 0), "actualAmount" from the second cell (index 1), "actualTransactionType" from the third cell (index 2)
                List<WebElement> cells = currentRow.findElements(By.tagName("td"));
                String actualDateTime = cells.get(0).getText().trim();
                String actualAmount = cells.get(1).getText().trim();
                String actualTransactionType = cells.get(2).getText().trim();
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