import org.openqa.selenium.*;

public class MainPage extends PageBase {
    private By linkToLoginPageLocator = By.xpath("//div[@id='p-vector-user-menu-overflow']//span[contains(text(), 'Log in')]//ancestor::a[1]");
    
    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://en.wikipedia.org/wiki/Main_Page");
    }

    public WikipediaLoginPage clickLinkToLoginPage() {
        WebElement linkToLoginPage = waitAndReturnElement(linkToLoginPageLocator);
        linkToLoginPage.click();
        return new WikipediaLoginPage(this.driver);
    }
}
