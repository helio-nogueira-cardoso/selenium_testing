import org.openqa.selenium.*;

public class LogoutPage extends PageBase<LogoutPage> implements UnambiguousPage<LogoutPage> {
    private By mainPageLink = By.xpath("//p[@id='mw-returnto']//a");

    public LogoutPage(WebDriver driver) {
        super(driver);
    }

    public LoggedOutMainPage clickMainPageLink() {
        waitAndReturnElement(mainPageLink).click();
        return new LoggedOutMainPage(this.driver);
    }

    public LogoutPage ensure() {
        waitElement(mainPageLink);
        return this;
    }
}
