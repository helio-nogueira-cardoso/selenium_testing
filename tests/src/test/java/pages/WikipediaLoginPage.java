package pages;
import org.openqa.selenium.*;

import abstractions.PageBase;
import interfaces.UnambiguousPage;

public class WikipediaLoginPage extends PageBase<WikipediaLoginPage> implements UnambiguousPage<WikipediaLoginPage> {
    private By usernameInputElementLocator = By.id("wpName1");
    private By passwordInputElementLocator = By.id("wpPassword1");
    private By loginButtonElementLocator = By.id("wpLoginAttempt");
    
    public WikipediaLoginPage(WebDriver driver) {
        super(driver);
        this.ensure();
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
        waitAndReturnElement(loginButtonElementLocator).click();

        return new LoginResultPage(this.driver);
    }

    public WikipediaLoginPage ensure() {
        waitElement(loginButtonElementLocator);
        return this;
    }
}
