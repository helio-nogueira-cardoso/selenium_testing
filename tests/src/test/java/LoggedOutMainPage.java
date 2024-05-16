import org.openqa.selenium.*;

public class LoggedOutMainPage extends PageBase<LoggedOutMainPage> implements UnambiguousPage<LoggedOutMainPage> {
    private By linkToLoginPageLocator = By.xpath("//div[@id='p-vector-user-menu-overflow']//span[contains(text(), 'Log in')]//ancestor::a[1]");
    private By welcomeTitleSpan = By.id("Welcome_to_Wikipedia");

    public LoggedOutMainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://en.wikipedia.org/wiki/Main_Page");
        this.ensure();
    }

    public WikipediaLoginPage clickLinkToLoginPage() {
        waitAndReturnElement(linkToLoginPageLocator).click();
        return new WikipediaLoginPage(this.driver);
    }

    public String getWelcomeTitleMessage() {
        return waitAndReturnElement(welcomeTitleSpan).getText();
    }

    public LoggedOutMainPage ensure() {
        waitElement(linkToLoginPageLocator);
        return this;
    }
}
