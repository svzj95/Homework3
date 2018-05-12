package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Stash;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;


public class SearchPage extends BasePage {

    @FindBy(xpath = "//a[contains(.,\"Перейти ко всем фильтрам\")]")
    WebElement fullFiltersButton;

    @FindBy(xpath = "//select//../button")
    WebElement showAsButton;

    @FindBy(xpath = "//input[@id=\"header-search\"]")
    WebElement searchField;

    @FindBy(xpath = "//div[contains(@class,\"n-snippet-list\")]/child::div[contains(@data-id,\"model\")]")
    List<WebElement> items;

    private WebDriverWait wait = new WebDriverWait(driver, 3);

    public SearchPage() throws InterruptedException {
        PageFactory.initElements(driver, this);
    }

    public SearchPage chooseCategory(String value) throws InterruptedException {
        driver.get((String) Stash.get("app.url"));
        driver.findElement(By.xpath("//a[@data-id='market']")).click();
        try{
            driver.findElement(By.xpath("//span[contains(.,'Да, спасибо')]")).click();
        }catch(NoSuchElementException e){}
        WebElement element;
        element = driver.findElement(By.xpath("//a[contains(@href,'catalog/54440?hid=198119&track=menu')]"));
        new Actions(driver).moveToElement(element).perform();
        wait.until(ExpectedConditions.visibilityOf(element = driver.findElement(By.xpath("//a[contains(.,'" + value + "')]"))));
        element.click();
        return this;
    }

    public SearchPage show12items() throws InterruptedException {
        try{
            new Actions(driver).moveToElement(showAsButton).perform();
            showAsButton.click();
            WebElement webElement = driver.findElement(By.xpath("//span[@class='select__text' and contains(.,\"Показывать по 12\")]"));
            webElement.click();
            Thread.sleep(5000);
        }catch(NoSuchElementException e){}
        finally {
            return new SearchPage();
        }
    }

    public SearchPage check12() throws InterruptedException {
        Assert.assertEquals(12, items.size());
        return this;
    }

    public SearchPage save1Name() {
        String name1 = items.get(0).findElement(By.xpath("//a[contains(@class,\"link n-link_theme_blue i-bem link_js_inited\")]")).getText();
        System.out.println(name1);
        Stash.put("name1", name1);
        return this;
    }

    public ItemCardPage searchItem() throws InterruptedException {
        searchField.sendKeys((CharSequence) Stash.get("name1"));
        searchField.submit();

        return new ItemCardPage();
    }

    public FilterPage goToFilterPage() throws InterruptedException {
        fullFiltersButton.click();
        return new FilterPage();
    }
}
