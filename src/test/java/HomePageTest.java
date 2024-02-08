import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

import pages.*;

public class HomePageTest {

    public WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\MYPC\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qajobbyapp.ccbp.tech/login");

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);

        loginPage.loginToApplication("rahul", "rahul@2021");
        String expectedUrl = "https://qajobbyapp.ccbp.tech/";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test(priority = 1)
    public void testHomepageUI() {
        String actualText = homePage.getHeadingText();
        String expectedText = "Find The Job That Fits Your Life";
        Assert.assertEquals(actualText, expectedText, "Heading text does not match");

        actualText = homePage.getDescriptionText();
        expectedText = "Millions of people are searching for jobs, salary information, company reviews. Find the job that fits your abilities and potential.";
        Assert.assertEquals(actualText, expectedText, "Description text does not match");
    }

    @Test(priority = 2)
    public void testFindJobsButton() {
        homePage.clickOnFindJobsButton();

        String expectedLoginUrl = "https://qajobbyapp.ccbp.tech/jobs";
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, expectedLoginUrl, "URLs do not match");
    }
}
