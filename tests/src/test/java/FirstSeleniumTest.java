import org.junit.*;
import java.net.URL;
import java.net.MalformedURLException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.junit.Assert.assertTrue;

public class FirstSeleniumTest {    
    private WebDriver driver;
    
    @Before
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        this.driver.manage().window().maximize();
    }

    @Test
    public void validAndInvalidLoginTest() {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.typeIntoUsernameInput("tomsmith");
        mainPage.typeIntoPasswordInput("SuperSecretPassword!");
        LoginResultPage loginResultPage = mainPage.clickLoginButton();

        assertTrue(loginResultPage.getBodyText().contains("You logged into a secure area!"));

        mainPage = loginResultPage.clickLogoutButton();

        mainPage.typeIntoUsernameInput("invalidusername");
        mainPage.typeIntoPasswordInput("invalidpassword");
        loginResultPage = mainPage.clickLoginButton();
        
        assertTrue(loginResultPage.getBodyText().contains("Your username is invalid!"));
    }  

    @After
    public void close() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}
