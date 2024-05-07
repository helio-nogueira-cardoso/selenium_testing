import org.openqa.selenium.*;

public class LoginResultPage extends PageBase {
    private By LogoutButtonElementLocator = By.className("button");
    
    public LoginResultPage(WebDriver driver) {
        super(driver);
    }

    public MainPage clickLogoutButton() {
        WebElement loginButtonElement = waitAndReturnElement(LogoutButtonElementLocator);
        loginButtonElement.click();

        return new MainPage(this.driver);
    }
}
