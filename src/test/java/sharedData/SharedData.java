package sharedData;

import loggerUtility.LoggerUtility;
import lombok.Getter;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

@Getter
public class SharedData {
    private WebDriver driver;

    @BeforeMethod
    public void prepareEnv() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless=new");
        //options.addArguments("--disable-search-engine-choice-screen");
        driver = new EdgeDriver(options);
        LoggerUtility.info("The browser is successfully opened.");
        driver.get("https://www.globalsqa.com/");
        //driver.manage().window().maximize();
        driver.manage().window().setSize(new Dimension(1920, 1080));  // Headless mode rendering fix
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