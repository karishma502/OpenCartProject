package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

import java.time.Duration;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class AccountRegistrationTest{
    WebDriver driver;
    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver=new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://tutorialsninja.com/demo/");
    }



    @Test
    public void verify_Account_Registry() throws InterruptedException {
        HomePage hp = new HomePage(driver);
        AccountRegistrationPage ar = new AccountRegistrationPage(driver);

        hp.clickOnMyAccount();
        hp.clickOnRegister();

        //call the functions for the fill the registration form

        ar.enterFirstName(randomString().toUpperCase());
        Thread.sleep(2000);
        ar.enterLastName(randomString().toUpperCase());
        ar.enterEmail(randomString()+"@gmail.com");
        ar.enterTelePhone(randomNumber());
        ar.enterPass("1234");
        ar.enterConfirmPass("1234");
        ar.clickOnPolicyAgree();
        ar.clickOnContinue();
       String confmsf= ar.getConfirmationMsg();

        Assert.assertEquals(confmsf,"Congratulations! Your new account has been successfully created!");
    }

    public String randomString(){
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        return generatedString;
    }
    public String randomNumber(){
        String generatedNum = RandomStringUtils.randomNumeric(9);
        return generatedNum;
    }

    @AfterClass
    public void tearDown(){
      //  driver.quit();
    }
}

