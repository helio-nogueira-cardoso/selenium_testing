package abstractions;

import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.searchResultPages.ArticlePage;
import pages.searchResultPages.ListOfSearchResultsPage;
import pages.searchResultPages.SearchResultPageHandler;

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
        this.wait = new WebDriverWait(this.driver, 5);
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

    public void pressBrowserBackButton() {
        this.driver.navigate().back();
    }

    public String getPageTitle() {
        return this.driver.getTitle();
    }

    protected void waitElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    } 

    protected WebElement waitAndReturnElement(By locator) {
        waitElement(locator);
        return this.driver.findElement(locator);
    }

    protected List<WebElement> waitAndReturnElements(By filter) {
        this.hold(3);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(filter));
        return this.driver.findElements(filter);
    }

    public String getBodyText() {
        return waitAndReturnElement(bodyElementLocator).getText();
    }    

    public SearchResultPageHandler search(String searchtext) {
        WebElement searchBarInputElement = waitAndReturnElement(searchBarInputElementLocator);
        searchBarInputElement.clear();
        searchBarInputElement.sendKeys(searchtext);
        
        waitAndReturnElement(searchButtonElementLocator).click();

        return new SearchResultPageHandler(this.driver);
    }

    public ArticlePage seachAndGoToFirstResult(String searchtext) {
        SearchResultPageHandler searchResultPageHandler = this.search(searchtext);

        if (searchResultPageHandler.isListOfSearchResults()) {
            ListOfSearchResultsPage listOfSearchResultsPage = searchResultPageHandler.listOfSearchResultsPage();
            return listOfSearchResultsPage.goToFirstArticle();
        } 
        
        return searchResultPageHandler.articlePage();
    }

    public void clickById(String id) {
        ((JavascriptExecutor) this.driver).executeScript("document.getElementById(\"" + id + "\").click();");
    }

    public boolean checkIfIsSelectedById(String id) {
        Object isSelected = ((JavascriptExecutor) this.driver).executeScript(
            "return document.getElementById(\"" + id + "\").checked;"
        );
    
        if (isSelected instanceof Boolean) {
            return (Boolean) isSelected;
        } else {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public T hold(int seconds) {
        try {
            (new WebDriverWait(this.driver, seconds)).until(ExpectedConditions.invisibilityOfElementLocated(bodyElementLocator));
        } catch (TimeoutException e) {
        }

        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T clickCorner() {
        Actions builder = new Actions(this.driver);
        builder.moveByOffset(1, 100).click().perform();
        this.hold(3);

        return (T) this;
    }
}
