import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;

public class WebElementsTest {
    WebDriver driver;

    @BeforeMethod
    public void open()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown()
    {
        driver.close();
    }

    @Test
    public void ex1() throws InterruptedException {
        // Navigating to the specified url
        driver.navigate().to("http://the-internet.herokuapp.com/dynamic_controls");
        Thread.sleep(500);

        //printing out page title
        System.out.println(driver.getTitle());

        //validating remove button is enabled
        WebElement Btn = driver.findElement(By.xpath("//form[@id='checkbox-example']//button"));
        if (Btn.isEnabled()){
            System.out.println("Remove button is enabled!");
        }else{
            System.out.println("Remove button is not enabled!");
        }

        //click on the remove button
        Btn.click();

        //wait for the changes after clicking
        Thread.sleep(5000);

        //check that button text has changed from 'Remove' to 'Add'
        if(Btn.getText().equals("Add")){
            System.out.println("Text has Changed!");
        }else{
            System.out.println("Text has not Changed!!!");
        }

        //check that text 'it's gone!' is displayed
        WebElement message = driver.findElement(By.xpath("//p[@id='message']"));
        if(message.getText().equals("It's gone!")) {
            System.out.println("It's gone is displayed");
        } else {
            System.out.println("It's gone is not displayed");
        }
    }

    @Test
    public void ex2 () throws InterruptedException {
        //Navigate to the specified url
        driver.navigate().to("http://the-internet.herokuapp.com/add_remove_elements/");

        //Click to 'Add Element' three times
        WebElement addBtn= driver.findElement(By.cssSelector(".example button"));
        for (int i = 0; i < 3; i++) {
            addBtn.click();
        }

        //Print out the last 'Delete' button element's text
        WebElement lastBtn = driver.findElement(By.cssSelector("#elements .added-manually:last-child"));
        System.out.println("Text of the last Delete button is: " + lastBtn.getText());

        //Validate count of 'Delete' elements is really 3
        List<WebElement> deleteElements = driver.findElements(By.cssSelector("#elements .added-manually"));
        if(deleteElements.size() == 3) {
            System.out.println("Count of 'Delete' Elements is really 3!");
        } else {
            System.out.println("Count of 'Delete' Elements is "+deleteElements.size());
        }

        //Click on the last 'Delete' button
        WebElement lastDeleteBtn = driver.findElement(By.xpath("//div[@id='elements']//button[contains(@class, 'added')][last()]"));
        lastDeleteBtn.click();


        //Validate that count of 'delete' elements has changed
        List<WebElement> deleteElementsafter = driver.findElements(By.cssSelector("#elements .added-manually"));
        if(deleteElementsafter.size() != 3){
            System.out.println("Number of Elements has changed");
        }else {
            System.out.println("Number of Elements has not changed! ! !");
        }
    }
}