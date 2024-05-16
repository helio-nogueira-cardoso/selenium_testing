import pages.LoggedOutMainPage;
import pages.LoginResultPage;
import pages.LogoutPage;
import pages.WikipediaLoginPage;
import pages.searchResults.ArticlePage;

import java.net.URL;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.github.cdimascio.dotenv.Dotenv;
import java.net.MalformedURLException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertTrue;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WikipediaTest {    
    private WebDriver driver;
    private String correctUsername;
    private String correctPassword;
    
    @BeforeAll
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
    
    @ParameterizedTest
    @CsvFileSource(resources = "/searchTestData.csv", numLinesToSkip = 1)
    public void searchTest(String searchText, String expectedArticleTitle) {
        ArticlePage article;
        LoggedOutMainPage loggedOutMainPage = new LoggedOutMainPage(this.driver);

        article = loggedOutMainPage.seachAndGoToFirstResult(searchText);

        assertTrue(article.getArticleTitle().equals(expectedArticleTitle));
    }

    @AfterAll
    public void close() throws InterruptedException {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}
