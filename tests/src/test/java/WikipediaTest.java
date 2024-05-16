import org.junit.*;
import java.net.URL;
import java.net.MalformedURLException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.github.cdimascio.dotenv.Dotenv;
import pages.LoggedOutMainPage;
import pages.LoginResultPage;
import pages.LogoutPage;
import pages.SearchResultArticlePage;
import pages.WikipediaLoginPage;

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
        LogoutPage logoutPage;

        LoggedOutMainPage loggedOutMainPage = new LoggedOutMainPage(this.driver);

        assertTrue(loggedOutMainPage.getWelcomeTitleMessage().contains("Welcome to Wikipedia"));

        loginPage = loggedOutMainPage
            .clickLinkToLoginPage();
        loginResultPage = loginPage
            .writeToUsernameInput("WRONGUSERNAME")
            .writeToPasswordInput("WRONGPASSWORD")
            .hold() // You might have to solve Recaptcha
            .clickLoginButton();

        assertTrue(loginResultPage.getBodyText().contains("Incorrect username or password entered"));

        loginResultPage = loginPage
            .writeToUsernameInput(this.correctUsername)
            .writeToPasswordInput(this.correctPassword)
            .hold() // You might have to solve Recaptcha
            .clickLoginButton();
        
        assertTrue(
            loginResultPage.userSpanElementExists() &&
            loginResultPage.getUserSpanText().contains(this.correctUsername)
        );

        logoutPage = loginResultPage
            .openUserMenu()
            .logout();

        assertTrue(logoutPage.getBodyText().contains("You are now logged out."));

        loggedOutMainPage = logoutPage.clickMainPageLink();

        assertTrue(loggedOutMainPage.getWelcomeTitleMessage().contains("Welcome to Wikipedia"));
    }  

    @Test
    public void searchTest() {
        SearchResultArticlePage article;

        LoggedOutMainPage loggedOutMainPage = new LoggedOutMainPage(this.driver);
        article = loggedOutMainPage.search("blaha lujza");

        assertTrue(article.getArticleTitle().equals("Lujza Blaha"));
    }

    @After
    public void close() throws InterruptedException {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}
