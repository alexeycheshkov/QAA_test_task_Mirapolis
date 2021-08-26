import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import main.Config;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    protected Browser browser;
    protected String testUrl = Config.get("test_protocol") + Config.get("test_host");

    @BeforeMethod
    public void setUp() {
        browser = AqualityServices.getBrowser();
        browser.maximize();
        browser.goTo(testUrl);
        browser.waitForPageToLoad();
    }

    @AfterMethod
    public void tearDown() {
        browser.quit();
    }
}
