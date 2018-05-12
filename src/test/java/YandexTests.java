import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.SearchPage;
import utils.Stash;
import utils.TestProperties;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

//@RunWith(Parameterized.class)
public class YandexTests {

    public static Properties properties = TestProperties.getInstance().getProperties();

//    @Parameterized.Parameter()
//    public String itemName;
//
//
//    @Parameterized.Parameters
//    public static Collection<Object[]> data() {
//        return Arrays.asList(new Object[][]{{"ipad"}, {"iphone"}});
//    }


    @BeforeClass
    public static void setUp() throws Exception {
        WebDriver driver;
        switch (properties.getProperty("browser")) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", properties.getProperty("webdriver.gecko.driver"));
                driver = new FirefoxDriver();
                break;
            default:
            case "chrome":
                System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
                driver = new ChromeDriver();
                break;
        }
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        Stash.put("app.url",properties.getProperty("app.url"));
        Stash.put("driver", driver);
    }

    @Test
    public void SearchTVTest() throws InterruptedException {
        new SearchPage().chooseCategory("Телевизоры").goToFilterPage().sendPricwFrom(20000).chooseVendor("Samsung","LG").showFiltered().show12items().check12().save1Name().searchItem();

    }

    @Test
    public void SearchHPTest() throws InterruptedException {
        new SearchPage().chooseCategory("Наушники").goToFilterPage().sendPricwFrom(5000).chooseVendor("Sennheiser").showFiltered().show12items().check12().save1Name().searchItem();


    }

    @AfterClass
    public static void tearDown() throws Exception {
        Stash.getDriver().quit();
    }
}
