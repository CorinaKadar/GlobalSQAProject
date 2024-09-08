package helperMethods;

import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;

@AllArgsConstructor
public class WindowMethods {
    public WebDriver driver;

    public void closeCurrentBrowser(){
        driver.quit();
    }

    public void refreshPage(){
        driver.navigate().refresh();
    }
}