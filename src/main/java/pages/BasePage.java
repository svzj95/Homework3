package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.Stash;

public class BasePage {
    WebDriver driver;
    public BasePage() throws InterruptedException {
        driver = Stash.getDriver();

    }
}
