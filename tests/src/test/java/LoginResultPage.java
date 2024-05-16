import org.openqa.selenium.*;

public class LoginResultPage extends PageBase<LoginResultPage> {
    private By userSpanElementLocator = By.xpath("//li[@id='pt-userpage-2']//span");
    private By userMenuOpenerElementLocator = By.id("vector-user-links-dropdown");
    private By logoutLinkElementLocator = By.xpath("//li[@id='pt-logout']//a");

    public LoginResultPage(WebDriver driver) {
        super(driver);
    }

    public boolean userSpanElementExists() {
        return elementExists(userSpanElementLocator);
    }

    public String getUserSpanText() {
        return waitAndReturnElement(userSpanElementLocator).getText();
    }

    public LoginResultPage openUserMenu() {
        waitAndReturnElement(userMenuOpenerElementLocator).click();
        return this;
    }

    public LogoutPage logout() {
        waitAndReturnElement(logoutLinkElementLocator).click();
        return new LogoutPage(this.driver);
    }
}
