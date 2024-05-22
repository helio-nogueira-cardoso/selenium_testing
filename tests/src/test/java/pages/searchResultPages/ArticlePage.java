package pages.searchResultPages;
import org.openqa.selenium.*;

import abstractions.PageBase;
import interfaces.UnambiguousPageInterface;

public class ArticlePage extends PageBase<ArticlePage> implements UnambiguousPageInterface<ArticlePage> {
    private By articleTitleElementLocator = ArticlePage.uniqueLocator();
    private By lastModificationFooterLocator = By.id("footer-info-lastmod");
    
    public ArticlePage(WebDriver driver) {
        super(driver);
        this.ensure();
    }

    public String getArticleTitle() {
        return waitAndReturnElement(articleTitleElementLocator).getText();
    }

    public String getLastModificationMessage() {
        return waitAndReturnElement(lastModificationFooterLocator).getText();
    }

    public ArticlePage ensure() {
        waitElement(ArticlePage.uniqueLocator());
        return this;
    }

    public static By uniqueLocator() {
        return By.xpath("//h1[@id='firstHeading']//span");
    }
}
