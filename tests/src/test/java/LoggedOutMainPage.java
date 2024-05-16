import org.openqa.selenium.*;

public class LoggedOutMainPage extends PageBase<LoggedOutMainPage> implements UnambiguousPage<LoggedOutMainPage> {
    private By linkToLoginPageLocator = By.xpath("//div[@id='p-vector-user-menu-overflow']//span[contains(text(), 'Log in')]//ancestor::a[1]");
    
    public LoggedOutMainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://en.wikipedia.org/wiki/Main_Page");
    }

    public boolean loginLinkExists() {
        return elementExists(linkToLoginPageLocator);
    }

    public WikipediaLoginPage clickLinkToLoginPage() {
        waitAndReturnElement(linkToLoginPageLocator).click();
        return new WikipediaLoginPage(this.driver);
    }

    public LoggedOutMainPage amIhere() {
        waitElement(linkToLoginPageLocator);
        return this;
    }
}
