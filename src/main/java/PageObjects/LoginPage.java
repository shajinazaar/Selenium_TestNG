package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    public WebDriver driver;
    By username = By.id("login_field");
    By password = By.id("password");
    By loginBtn = By.xpath("//*[@type='submit']");
    By signInText = By.xpath("//*[text()='Sign in to GitHub']");

    public LoginPage(WebDriver driver) {this.driver=driver; }
    public WebElement enterEmail() { return driver.findElement(username);}

    public WebElement enterPassword(){return driver.findElement(password);}

    public WebElement clickLoginButton() {return driver.findElement(loginBtn);}

    public WebElement SignInText() {return  driver.findElement(signInText);}

}
