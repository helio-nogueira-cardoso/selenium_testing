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
    
    protected boolean elementExists(By locator) {
        try {
            this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (NoSuchElementException e) {
            System.out.println("No such element.");
            return false;
        } catch (TimeoutException e) {
            System.out.println("Timeout while looking for element.");
            return false;
        }

        return true;
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

    public void hold() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(bodyElementLocator));
        } catch (TimeoutException e) {
        }
    }
}
