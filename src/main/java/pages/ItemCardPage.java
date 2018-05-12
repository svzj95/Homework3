package pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Stash;

public class ItemCardPage extends BasePage {

    @FindBy(xpath = "//h1[@class=\"title title_size_28 title_bold_yes\"]")
    WebElement titleItem;

    public ItemCardPage() throws InterruptedException {
        PageFactory.initElements(driver, this);
    }

    public void checkTitle(){
        Assert.assertEquals(Stash.get("name1"), titleItem.getText());
    }
}
