package pages.searchResults;
import org.openqa.selenium.*;

import abstractions.PageBase;
import pages.WikipediaLoginPage;

public class SearchResultPageHandler extends PageBase<WikipediaLoginPage> {    
    public SearchResultPageHandler(WebDriver driver) {
        super(driver);
    }

    public boolean isArticle() {
        try {
            waitElement(ArticlePage.uniqueLocator());
        } catch (TimeoutException e) {
            return false;
        }

        return true;
    }

    public boolean isListOfSearchResults() {
        try {
            waitElement(ListOfSearchResultsPage.uniqueLocator());
        } catch (TimeoutException e) {
            return false;
        }

        return true;        
    }

    public ArticlePage articlePage() {
        return new ArticlePage(this.driver);
    }

    public ListOfSearchResultsPage listOfSearchResultsPage() {
        return new ListOfSearchResultsPage(this.driver);
    }
}
