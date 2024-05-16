import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PageBase<T> {    
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

    protected void waitElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    } 

    protected WebElement waitAndReturnElement(By locator) {
        waitElement(locator);
        return this.driver.findElement(locator);
    }

    public String getBodyText() {
        return waitAndReturnElement(bodyElementLocator).getText();
    }    

    @SuppressWarnings("unchecked")
    public T hold() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(bodyElementLocator));
        } catch (TimeoutException e) {
        }

        return (T) this;
    }
}
