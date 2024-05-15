import org.openqa.selenium.*;

public class LoginResultPage extends PageBase {
    private By userSpanElementLocator = By.xpath("//li[@id='pt-userpage-2']//span");

    public LoginResultPage(WebDriver driver) {
        super(driver);
    }

    public boolean userSpanElementExists() {
        return elementExists(userSpanElementLocator);
    }

    public String getUserSpanText() {
        WebElement userSpanElement = waitAndReturnElement(userSpanElementLocator);
        return userSpanElement.getText();
    }
}
