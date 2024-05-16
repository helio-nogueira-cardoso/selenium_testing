import org.openqa.selenium.*;

public class LogoutResultPage extends PageBase {
    private By mainPageLink = By.xpath("//p[@id='mw-returnto']//a");

    public LogoutResultPage(WebDriver driver) {
        super(driver);
    }

    public MainPage clickMainPageLink() {
        waitAndReturnElement(mainPageLink).click();
        return new MainPage(this.driver);
    }
}
