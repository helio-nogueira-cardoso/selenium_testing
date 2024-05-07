import org.openqa.selenium.*;

public class MainPage extends PageBase {
    private By usernameInputElementLocator = By.id("username");
    private By passwordInputElementLocator = By.id("password");
    private By LoginButtonElementLocator = By.className("radius");
    
    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://the-internet.herokuapp.com/login");

    }

    public void typeIntoUsernameInput(String username) {
        WebElement usernameInputElement = waitAndReturnElement(usernameInputElementLocator);
        usernameInputElement.sendKeys(username);
    }

    public void typeIntoPasswordInput(String password) {
        WebElement passwordInputElement = waitAndReturnElement(passwordInputElementLocator);
        passwordInputElement.sendKeys(password);
    }

    public LoginResultPage clickLoginButton() {
        WebElement loginButtonElement = waitAndReturnElement(LoginButtonElementLocator);
        loginButtonElement.click();
        return new LoginResultPage(this.driver);
    }
}
