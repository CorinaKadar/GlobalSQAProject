package helperMethods;

import lombok.AllArgsConstructor;
import objectData.AlertMessageObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@AllArgsConstructor
public class AlertMethods {
    public WebDriver driver;

    public void waitForAlert() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert() {
        waitForAlert();
        Alert alertOK = driver.switchTo().alert();
        alertOK.accept();
    }

    public String extractNumberValueFromAlertMessage() {
        waitForAlert();
        Alert alertAccountOK = driver.switchTo().alert();
        String alertMessage = alertAccountOK.getText();
        String[] parts = alertMessage.split(":");
        return parts[1].trim();
    }

    // Extracts and returns the parsed parts of an alert message (both text and number) as an AlertMessageObject object that encapsulates both pieces of information (alertText and alertNumberValue)
    public AlertMessageObject extractPartsFromAlertMessage() {
        waitForAlert();
        Alert alert = driver.switchTo().alert();
        String alertMessage = alert.getText();
        String[] parts = alertMessage.split(":");
        String alertText = parts[0].trim();
        String alertNumberValue = parts[1].trim();
        return new AlertMessageObject(alertText, alertNumberValue);
    }
}