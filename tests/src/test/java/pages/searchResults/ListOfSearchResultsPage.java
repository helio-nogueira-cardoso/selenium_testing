package pages.searchResults;

import org.openqa.selenium.*;

import abstractions.PageBase;
import interfaces.UnambiguousPageInterface;

public class ListOfSearchResultsPage extends PageBase<ListOfSearchResultsPage> implements UnambiguousPageInterface<ListOfSearchResultsPage> {
    private By resultlinksElementsFilter = By.xpath("//div[@class='mw-search-result-heading']//a");
    
    public ListOfSearchResultsPage(WebDriver driver) {
        super(driver);
        this.ensure();
    }

    public ArticlePage goToFirstArticle() {
        waitAndReturnElements(resultlinksElementsFilter).get(0).click();
        return new ArticlePage(this.driver);
    }

    public ListOfSearchResultsPage ensure() {
        waitElement(ListOfSearchResultsPage.uniqueLocator());
        return this;
    }

    public static By uniqueLocator() {
        return By.xpath("//div[@id='mw-search-top-table']//span[contains(@class, 'labelElement-label')]");
    }
}
