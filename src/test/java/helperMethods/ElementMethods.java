package helperMethods;

import loggerUtility.LoggerUtility;
import lombok.AllArgsConstructor;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

@AllArgsConstructor
public class ElementMethods {
    public WebDriver driver;

    public void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public List<WebElement> refreshTransactionTableRowValues(String xpath) {
        return driver.findElements(By.xpath(xpath));
    }

    // Generalized method to get the value of the @FindBy annotation
    public String getFindByAnnotationValue(Object pageClassInstance, String fieldName) {
        try {
            // Get the class of the page instance passed
            Class<?> clazz = pageClassInstance.getClass();

            // Get the field by its name
            Field field = clazz.getDeclaredField(fieldName);

            // Check if the field is annotated with @FindBy
            if (field.isAnnotationPresent(FindBy.class)) {
                FindBy findBy = field.getAnnotation(FindBy.class);

                // Extract the value from the annotation based on the type of locator
                if (!findBy.xpath().isEmpty()) {
                    return findBy.xpath();
                } else if (!findBy.css().isEmpty()) {
                    return findBy.css();
                } else if (!findBy.id().isEmpty()) {
                    return findBy.id();
                } else {
                    return "No valid locator found in @FindBy annotation.";
                }
            } else {
                return "No @FindBy annotation present on the field: " + fieldName;
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return "Field not found: " + fieldName;
        }
    }

    public WebElement waitForCookieToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickElement(WebElement element) {
        waitForElementToBeVisible(element);
        element.click();
    }

    public void hoverOverElement(WebElement element) {
        waitForElementToBeVisible(element);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public void selectByValue(WebElement element, String text) {
        waitForElementToBeVisible(element);
        Select customerSelect = new Select(element);
        customerSelect.selectByVisibleText(text);
        element.click();
    }

    public void insertValue(WebElement element, String text) {
        waitForElementToBeVisible(element);
        element.sendKeys(text);
    }

    public String getCurrentDate() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(currentDate);
    }

    public void pressElement(WebElement element, Keys key) {
        waitForElementToBeVisible(element);
        element.sendKeys(key);
    }

    public String getText(WebElement element) {
        waitForElementToBeVisible(element);
        return element.getText();
    }

    // Method to avoid parsing, because Expected Data cannot be parsed due to the format used for filtering
    // Converts a date string from the format "MM/dd/yyyy" to "MMM dd, yyyy"
    public String formatExpectedTransactionDate(String inputDate) {
        // Split the input date string into parts (month, day, year)
        String[] dateParts = inputDate.split("/");

        // Extract the month, day, and year as integers
        Integer month = Integer.parseInt(dateParts[0]);
        Integer day = Integer.parseInt(dateParts[1]);
        Integer year = Integer.parseInt(dateParts[2]);

        // A Calendar object is created using Calendar.getInstance(). and sets the extracted date values
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month - 1); // Calendar.MONTH is 0-based (i.e., January is 0, February is 1, and so on), so month - 1 is used to correctly set the month.
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.YEAR, year);

        // Retrieves the abbreviated month name (e.g., "Jan", "Feb", etc.) using calendar.getDisplayName.
        String monthName = calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH);

        // Format the output date string as "MMM dd, yyyy"
        return String.format("%s %02d, %d", monthName, day, year);
    }

    // The try-catch block is necessary to this method, it's used to handle potential ParseException that might occur when trying to parse the inputDate string into a Date object.
    // It can only be used for Actual Date because Expected Date cannot be parsed due to the format used for filtering
    // This method is intended to take an actual displayed date string (MMM dd, yyyy HH:mm:ss a) as String inputDate, parse it into a Date object (MMM dd, yyyy), and then format it back into a string in the same format: "MMM dd, yyyy" as a String in this format is needed.
    public String formatActualTransactionDate(String inputDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        Date date = null;
        try {
            date = dateFormat.parse(inputDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return dateFormat.format(date);
    }
}