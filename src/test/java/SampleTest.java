import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.NotificationPage;
import org.example.ExcelDataReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class SampleTest {

    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    NotificationPage notificationPage;

    @BeforeClass
    public void setUp() {
        // Initialize ChromeDriver instance
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        notificationPage = new NotificationPage(driver);

    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws Exception {
        // Return data from your ExcelDataReader
        String excelFilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\Data.xlsx";
        return ExcelDataReader.getDataFromExcel("LOGIN", excelFilePath);
    }

    @Test(dataProvider = "loginData", priority = 1)
    public void VerifyLogin(String username, String password) {
        login(username, password);
    }

    @Test(priority = 2, dependsOnMethods = "VerifyLogin")
    public void VerifyHomePage() {
        String homePageText = homePage.getHomeText().getText().trim();
        Assert.assertEquals(homePageText, "Home");
        System.out.println(homePageText.contains("Home") ? "Login successful." : "Login failed.");
    }

    @Test(priority = 3, dependsOnMethods = "VerifyLogin")
    public void VerifyRepoLinks() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement ulElement = wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.ulElement));
        List<WebElement> liElements = ulElement.findElements(By.tagName("li"));
        System.out.println("Number of Repo links : " + liElements.size());

        for (WebElement liElement : liElements) {
            String repoLinks = liElement.getText().trim();
            System.out.println("Respository Links : " + repoLinks);
        }
    }

    @Test(priority = 3, dependsOnMethods = "VerifyLogin")
    public void VerifySecurityAlerts(){
        notificationPage.getNotificationBtn().click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement securityAlertText = wait.until(ExpectedConditions.visibilityOfElementLocated(notificationPage.securityAlertText));
        List<WebElement> securityAlertElements = driver.findElements(notificationPage.securityAlertText);

        int securityAlertCount = securityAlertElements.size();

        if (securityAlertCount > 0) {
            System.out.println("Number of security alert found: " + securityAlertCount);
            System.out.println("At least one or more security alert is present on the page.");
        } else {
            System.out.println("No security alert found on the page.");
        }
    }

    private void login(String username, String password) {
        driver.get("https://github.com/login");
        String text = loginPage.SignInText().getText().trim();
        Assert.assertEquals(text, "Sign in to GitHub");
        loginPage.enterEmail().sendKeys(username);
        loginPage.enterPassword().sendKeys(password);
        loginPage.clickLoginButton().click();

    }
}
