package pages;
import org.openqa.selenium.*;

import abstractions.PageBase;
import interfaces.UnambiguousPageInterface;

public class LogoutPage extends PageBase<LogoutPage> implements UnambiguousPageInterface<LogoutPage> {
    private By mainPageLink = By.xpath("//p[@id='mw-returnto']//a");

    public LogoutPage(WebDriver driver) {
        super(driver);
        this.ensure();
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
