package pages;
import org.openqa.selenium.*;

import abstractions.PageBase;
import interfaces.UnambiguousPage;

public class SearchResultArticlePage extends PageBase<WikipediaLoginPage> implements UnambiguousPage<SearchResultArticlePage> {
    private By articleTitleElementLocator = By.xpath("//h1[@id='firstHeading']//span");
    
    public SearchResultArticlePage(WebDriver driver) {
        super(driver);
        this.ensure();
    }

    public String getArticleTitle() {
        return waitAndReturnElement(articleTitleElementLocator).getText();
    }

    public SearchResultArticlePage ensure() {
        waitElement(articleTitleElementLocator);
        return this;
    }
}
