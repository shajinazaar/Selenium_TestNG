package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NotificationPage {

    WebDriver driver;

    By notificationBtn = By.xpath("//a[@id='AppHeader-notifications-button']");

    public By securityAlertText = By.xpath("//span[text()='security alert']");


    public NotificationPage(WebDriver driver) {this.driver=driver; }

    public WebElement getNotificationBtn() { return driver.findElement(notificationBtn);}

}
