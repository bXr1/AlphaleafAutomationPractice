package practice;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ComplexActionTest {

    private ChromeDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager
                .chromedriver()
                .setup();

        driver = new ChromeDriver();
        driver.manage()
                .window()
                .maximize();
    }

    @AfterMethod
    public void cleanUp() {
        pausefor(6);
        driver.quit();
    }

    public void pausefor(int second) {
        try{
            Thread.sleep(second * 1000L);
        }catch (InterruptedException ignore) {
            // DO NOTHING
        }
    }

    @Test
    public void doubleClick() {
        driver.get("http://webdriveruniversity.com/Actions/index.html");

        // 1. Double click one of the button
        By loc_yellow_button = By.cssSelector("div[id='double-click']");
        WebElement yellowBttnElement = driver.findElement(loc_yellow_button);


        // 2. Complex Actions alert! Double click
        Actions complexActions = new Actions(driver);

        // Fluent Style method calling
        complexActions
                .doubleClick(yellowBttnElement)  // complex action
                .perform();                      // I stated everything I want you to do!

    }

    @Test
    public void clickAndHold() {
        driver.get("http://webdriveruniversity.com/Actions/index.html");

        // 1. Identify the button we want to click and hold
        By loc_black_bttn = By.cssSelector("div[id='click-box']");
        WebElement blackButtonElement = driver.findElement(loc_black_bttn);
        pausefor(3);

        Actions actions = new Actions(driver);
        actions.clickAndHold(blackButtonElement)
                .perform();

    }

    @Test
    public void dragAndDrop() {
        driver.get("http://webdriveruniversity.com/Actions/index.html");

        // 1. Identify the two element involved
        By loc_from = By.id("draggable");
        By loc_target = By.id("droppable");

        WebElement fromElement = driver.findElement(loc_from);
        WebElement targetElement = driver.findElement(loc_target);
        pausefor(4);

        // 2. Create and actions object
        Actions actions = new Actions(driver);
        actions.dragAndDrop(fromElement, targetElement)
                .perform();

    }

    @Test
    public void hoverOver() {
        driver.get("http://webdriveruniversity.com/Actions/index.html");

        By loc_third = By.xpath("(//button[@class='dropbtn'])[3]");
        WebElement thirdBttnElement = driver.findElement(loc_third);
        pausefor(4);

        // Create an Actions object
        Actions actions = new Actions(driver);
        actions.moveToElement(thirdBttnElement)
                .perform();
        pausefor(3);

        // Hover over and click the option that showed up
        By loc_second = By.xpath("(//button[@class='dropbtn'])[2]");
        WebElement secondBttnElement = driver.findElement(loc_second);

        By loc_option = By.xpath("(//div[@class='dropdown-content']/a)[2]");
        WebElement option1 = driver.findElement(loc_option);
        pausefor(2);

        actions
                .moveToElement(secondBttnElement)   // hover over
                .click(option1)                     // click the option "Link1"
                .perform();
    }
}
// this is end of the class code.