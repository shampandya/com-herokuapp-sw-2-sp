package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class LoginTest extends BaseTest {
     String baseUrl ="http://the-internet.herokuapp.com/login";

    @Before
    public void setUp(){
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials(){
    driver.findElement(By.xpath("//input[@id='username']")).sendKeys("tomsmith");
    driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
    driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();
    String expectedText = "Secure Area";
    String actualText = driver.findElement(By.xpath("//div[@class='example']//h2")).getText();
        Assert.assertEquals("Verify two texts: ",expectedText,actualText);
}
    @Test
    public void verifyTheUsernameErrorMessage(){
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("tomsmith1");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();
        String expectedText = "Your username is invalid!";
        String actualText = driver.findElement(By.xpath("//div[@id='flash']")).getText();
            Assert.assertEquals("Verify two texts: ",expectedText,actualText);
    }

    @Test
    public void verifyThePasswordErrorMessage(){
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("tomsmith1");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword");
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();
        String expectedText = "Your password is invalid!";
        String actualText = driver.findElement(By.xpath("//div[@class='flash error']['             Your password is invalid!             ']")).getText();
        Assert.assertEquals("Verify two texts: ",expectedText,actualText);
    }

    @After
    public void tearDown(){
        closeBrowser();
    }
}
