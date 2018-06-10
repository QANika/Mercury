import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Login {

    // instruct TestNG to consider MercuryLogin as a runnable test
    @Test

    public void MercuryLogin() {

        //create new instance of Firefox Driver
        WebDriver driver = new FirefoxDriver();

        // and now use this to get newtours.demoaut.com
        driver.get("http://newtours.demoaut.com/");

        // read the title of the page and put it in results
        System.out.println("Page title is: " + driver.getTitle());

        // find the text input element 'username' by its name
        WebElement element = driver.findElement(By.name("userName"));

        // enter username
        element.sendKeys("mercury");

        // find the text input element 'password' by its name
        element = driver.findElement(By.name("password"));

        // enter password
        element.sendKeys("mercury");

        // find the button login by its name
        element = driver.findElement(By.name("login"));

        // click login button
        element.click();

        // try to sleep (wait) for 5 seconds (using exception handler)
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        // read the title of the page and output it into results - needed for debug purpose
        System.out.println("Page title is: " + driver.getTitle());

        // verify the title of the page
        Assert.assertEquals(driver.getTitle(),"Find a Flight: Mercury Tours:");

        // close the browser
        driver.close();
    }
}
