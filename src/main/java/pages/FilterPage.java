package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FilterPage extends BasePage {
    @FindBy(xpath="//input[@id=\"glf-pricefrom-var\"]")
    WebElement priceFromField;

    @FindBy(xpath="//span[contains(.,\"Показать подходящие\")]/..")
    WebElement showFiltered;

    public FilterPage() throws InterruptedException {
        PageFactory.initElements(driver, this);
    }

    public FilterPage sendPricwFrom(float value){
        priceFromField.sendKeys(Float.toString(value));
        return this;
    }

    public FilterPage chooseVendor(String... values){
        for(String i : values) {
            driver.findElement(By.xpath("//span[contains(.,'Производитель')]/../../..//label[contains(.,'" + i + "')]")).click();
        }
        return this;
    }

    public SearchPage showFiltered() throws InterruptedException {
        showFiltered.click();
        return new SearchPage();
    }
}
