import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PageBase {    
    protected WebDriver driver;
    private WebDriverWait wait;
    private By bodyElementLocator = By.tagName("body");

    public PageBase(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 10);

    }
    
    protected WebElement waitAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }


    protected void waitElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public String getBodyText() {
        WebElement bodyElement = waitAndReturnElement(bodyElementLocator);
        return bodyElement.getText();
    }    
}
