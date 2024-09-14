package pages;

import loggerUtility.LoggerUtility;
import objectData.BankCustomerObject;
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

    @FindBy(xpath = "//input[@id='start']")
    private WebElement startDateElement;

    @FindBy(xpath = "//button[@class='btn logout' and contains(text(), 'Logout')]")
    private WebElement logoutButton;

    @FindBy(xpath = "//table[@class='table table-bordered table-striped']//tbody/tr")
    private List<WebElement> transactionsTableRowValues;

    @FindBy(xpath = "//table[@class='table table-bordered table-striped']//tbody/tr/td[1]")
    private List<WebElement> columnDateTimeValues;

    @FindBy(xpath = "//table[@class='table table-bordered table-striped']//tbody/tr/td[2]")
    private List<WebElement> columnAmountValues;

    @FindBy(xpath = "//table[@class='table table-bordered table-striped']//tbody/tr/td[3]")
    private List<WebElement> columnTransactionTypeValues;

    public void filterTransactionsByStartDateField(String formattedCurrentDateForFilter) {
        LoggerUtility.info("Starting to filter the transactions by date and time: " + formattedCurrentDateForFilter);
        try {
            elementMethods.waitForPageToLoad();
            LoggerUtility.info("Page load completed successfully.");
            String extractedXPath = elementMethods.getFindByAnnotationValue(this, "startDateElement");
            LoggerUtility.info("Successfully extracted the required xpath string value.");
            startDateElement = elementMethods.refreshElement(extractedXPath);
            LoggerUtility.info("Successfully identified the Start Date field.");
            elementMethods.fluentWaitForElementToBeClickable(startDateElement);
            LoggerUtility.info("'Start Date' field is clickable.");
            elementMethods.clearField(startDateElement);
            LoggerUtility.info("'Start Date' field is clear.");
            elementMethods.insertValue(startDateElement, formattedCurrentDateForFilter);
            LoggerUtility.info("The start date: " + formattedCurrentDateForFilter + " is successfully inserted into the 'Start Date' field.");
            elementMethods.pressElement(startDateElement, Keys.ENTER);
            LoggerUtility.info("Successfully pressed 'Enter' to submit the start date value.");
            elementMethods.waitForPageToLoad();
            LoggerUtility.info("Page load completed successfully.");
            LoggerUtility.info("Successfully filtered the transactions by the Start Date field: " + formattedCurrentDateForFilter);
        } catch (Exception e) {
            LoggerUtility.error("An error occurred while filtering transactions by the date and time: " + formattedCurrentDateForFilter + ". Error: " + e.getMessage());
            throw e;
        }
    }

    public void validateTransactionTableRows(List<BankCustomerObject> expectedValues) {
        LoggerUtility.info("Starting the validation process for the Transactions table rows.");
        try {
            elementMethods.waitForPageToLoad();
            LoggerUtility.info("Page load completed successfully.");
            String extractedXPath = elementMethods.getFindByAnnotationValue(this, "transactionsTableRowValues");
            LoggerUtility.info("Successfully extracted the required xpath string value.");
            transactionsTableRowValues = elementMethods.refreshElements(extractedXPath);
            LoggerUtility.info("Successfully identified the Transactions table rows.");
            for (Integer i = 0; i < transactionsTableRowValues.size(); i++) {
                String actualDateTime = columnDateTimeValues.get(i).getText().trim();
                String actualAmount = columnAmountValues.get(i).getText().trim();
                String actualTransactionType = columnTransactionTypeValues.get(i).getText().trim();
                String actualFormattedDate = elementMethods.formatActualTransactionDate(actualDateTime); // This formatting ensures that the date values are in a consistent format for accurate comparison.
                LoggerUtility.info("Successfully extracted the Actual values from row " + (i + 1) + " - Date-Time: " + actualFormattedDate + ", Amount: " + actualAmount + ", Transaction Type: " + actualTransactionType);

                BankCustomerObject expectedTransaction = expectedValues.get(i);
                String expectedFormattedDate = elementMethods.formatExpectedTransactionDate(expectedTransaction.getDateTime()); // This formatting ensures that the date values are in a consistent format for accurate comparison.
                LoggerUtility.info("Successfully extracted the Expected values for row " + (i + 1) + " - Date-Time: " + expectedFormattedDate + ", Amount: " + expectedTransaction.getAmount() + ", Transaction Type: " + expectedTransaction.getTransactionType());

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
}