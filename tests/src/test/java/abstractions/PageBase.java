package abstractions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.SearchResultArticlePage;

import org.openqa.selenium.support.ui.ExpectedConditions;

/*
 * T: same class that extends
 */
public abstract class PageBase<T> {    
    protected WebDriver driver;
    private WebDriverWait wait;
    private By bodyElementLocator = By.tagName("body");
    private By searchBarInputElementLocator = By.xpath("//form[@id='searchform']//input[contains(@class, 'cdx-text-input')]");
    private By searchButtonElementLocator = By.xpath("//form[@id='searchform']//button[contains(@class, 'cdx-button')]");

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

    public SearchResultArticlePage search(String searchtext) {
        WebElement searchBarInputElement = waitAndReturnElement(searchBarInputElementLocator);
        searchBarInputElement.clear();
        searchBarInputElement.sendKeys(searchtext);
        
        waitAndReturnElement(searchButtonElementLocator).click();

        return new SearchResultArticlePage(this.driver);
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
