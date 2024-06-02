package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    WebDriver driver;

    By homeText = By.xpath("//h2[@data-target='feed-container.feedTitle']");

   public By ulElement = By.xpath("//div/div/div/ul[@data-filterable-for='dashboard-repos-filter-left'][1]");
    public HomePage(WebDriver driver) {this.driver=driver; }

    public WebElement getHomeText() { return driver.findElement(homeText);}

   public WebElement getUlElements() { return driver.findElement(ulElement);}

}
