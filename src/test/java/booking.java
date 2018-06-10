/*Write a code to book a ticket at http://newtours.demoaut.com.
A screenshot should be taken on every page as the control progresses from one page to next page irrespective of the fact that the test case is passing or failing. As the work on 1 page gets completed take a screenshot.
*/

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class booking {
    static WebDriver driver;

    @BeforeClass
    public void setUP() throws Exception {
        System.setProperty("webdriver.chrome.driver","C:\\ChromeDriver/chromedriver.exe");
        driver = new ChromeDriver();

        driver.get("http://newtours.demoaut.com/mercurywelcome.php");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    /**
     * Do login to mercury tours
     */
    @Test(priority = 1, description = "Login to mercury tours..")
    public void login() {
        driver.findElement(By.name("userName")).sendKeys("mercury");
        driver.findElement(By.name("password")).sendKeys("mercury");
        driver.findElement(By.name("login")).click();
    }

    /**
     * Click on continue Find Flights"
     */
    @Test(priority = 2, description = "Click on continue Find Flights")
    public void findFlightsContinue()
    {
        driver.findElement(By.name("findFlights")).click();
    }

    /**
     * Click on continue reserve Flights"
     */
    @Test(priority = 3, description = "Click on continue reserve Flights")
    public void reserveFlightsContinue()
    {
        driver.findElement(By.name("reserveFlights")).click();
    }

    /**
     * Enter details and click on continue book Flights"
     */
    @Test(priority = 4, description = "Click on continue book Flights")
    public void bookFlightsContinue()
    {
        driver.findElement(By.name("passFirst0")).sendKeys("Nika");
        driver.findElement(By.name("passLast0")).sendKeys("Douglas");
        driver.findElement(By.name("creditnumber")).sendKeys("9579212515");
        driver.findElement(By.name("buyFlights")).click();
    }

    /**
     * Logout after booking flights.
     */
    @Test(priority = 5, description = "Logout after booking flights.")
    public void doLogOut()
    {
        driver.findElement(By.xpath("//a[@href='mercurysignoff.php']/img")).click();
        driver.findElement(By.linkText("Home")).click();
        Assert.assertEquals(driver.getTitle(), "Welcome: Mercury Tours","Not able to log out..");
    }


    @AfterMethod
    public void doAfterTest() throws Exception
    {
        try {
            takeScreenShot();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new Exception("Call take screen shot method is failed..");
        }
    }

    /**
     * This function will take screenshot
     *
     * @param webdriver
     * @param fileWithPath
     * @throws Exception
     */
    public void takeScreenShot() throws Exception {
        try {
            // Convert web driver object to TakeScreenshot
            TakesScreenshot scrShot = ((TakesScreenshot) driver);
            // Call getScreenshotAs method to create image file
            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

            String img = (new SimpleDateFormat("ddmmyyyhhss")).format(new Date());
            String filepath = System.getProperty("user.dir") + "\\src\\Module7IntroductionToTestNG\\ScreenShots\\"+ img + ".jpg";
            System.out.println("Filepath: "+filepath);

            // Move image file to new destination
            File DestFile = new File(filepath.trim());
            // Copy file at destination
            FileUtils.copyFile(SrcFile, DestFile);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new Exception("Capture screen shot is failed..");
        }
    }


}
