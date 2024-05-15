import org.openqa.selenium.*;

public class WikipediaLoginPage extends PageBase {
    private By usernameInputElementLocator = By.id("wpName1");
    private By passwordInputElementLocator = By.id("wpPassword1");
    private By loginButtonElementLocator = By.id("wpLoginAttempt");
    
    public WikipediaLoginPage(WebDriver driver) {
        super(driver);
    }

    public WikipediaLoginPage writeToUsernameInput(String username) {
        WebElement usernameInputlElement = waitAndReturnElement(usernameInputElementLocator);
        usernameInputlElement.clear();
        usernameInputlElement.sendKeys(username);
        return this;
    }

    public WikipediaLoginPage writeToPasswordInput(String password) {
        WebElement passwordInputlElement = waitAndReturnElement(passwordInputElementLocator);
        passwordInputlElement.clear();
        passwordInputlElement.sendKeys(password);
        return this;
    }

    public LoginResultPage clickLoginButton() {
        WebElement loginButton = waitAndReturnElement(loginButtonElementLocator);
        loginButton.click();

        return new LoginResultPage(this.driver);
    }
}
