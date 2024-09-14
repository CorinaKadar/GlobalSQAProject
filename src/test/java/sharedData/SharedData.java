package sharedData;

import loggerUtility.LoggerUtility;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

@Getter
public class SharedData {
    private WebDriver driver;

    @BeforeMethod
    public void prepareEnv() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--disable-search-engine-choice-screen");
        options.addArguments("window-size=1920x1080");
        driver = new ChromeDriver(options);
        LoggerUtility.info("The browser is successfully opened.");
        driver.get("https://www.globalsqa.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        LoggerUtility.info("The page is successfully loaded.");
    }

    @AfterMethod
    public void clearEnv(ITestResult result) {
        if (!result.isSuccess()) {
            LoggerUtility.error(result.getThrowable().getMessage());
        }
        driver.quit();
        LoggerUtility.info("The browser is successfully closed.");
    }
}