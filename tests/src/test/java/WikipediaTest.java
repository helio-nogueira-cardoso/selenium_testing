import org.junit.*;
import java.net.URL;
import java.net.MalformedURLException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.github.cdimascio.dotenv.Dotenv;

import static org.junit.Assert.assertTrue;

public class WikipediaTest {    
    private WebDriver driver;
    private String correctUsername;
    private String correctPassword;
    
    @Before
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        this.driver.manage().window().maximize();

        Dotenv dotenv = Dotenv.load();
        correctUsername = dotenv.get("USERNAME");
        correctPassword = dotenv.get("PASSWORD");
    }

    @Test
    public void validAndInvalidLoginTest() {
        WikipediaLoginPage loginPage;
        LoginResultPage loginResultPage;

        MainPage mainPage = new MainPage(this.driver);;

        loginPage = mainPage
            .clickLinkToLoginPage();
        loginResultPage = loginPage
            .writeToUsernameInput("WRONGUSERNAME")
            .writeToPasswordInput("WRONGPASSWORD")
            .clickLoginButton();

        assertTrue(loginResultPage.getBodyText().contains("Incorrect username or password entered"));

        loginResultPage = loginPage
            .writeToUsernameInput(this.correctUsername)
            .writeToPasswordInput(this.correctPassword)
            .clickLoginButton();
        
        assertTrue(
            loginResultPage.userSpanElementExists() &&
            loginResultPage.getUserSpanText().contains(this.correctUsername)
        );
    }  

    @After
    public void close() throws InterruptedException {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}
