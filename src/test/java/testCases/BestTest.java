package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.time.Duration;

public class BestTest {

   public WebDriver driver;
   public Logger logger;
    @BeforeClass
    public void setUp(){

        logger = LogManager.getLogger(this.getClass());
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver=new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://tutorialsninja.com/demo/");
    }


    public String randomString(){
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        return generatedString;
    }
    public String randomNumber(){
        String generatedNum = RandomStringUtils.randomNumeric(9);
        return generatedNum;
    }
    public String randomAlphaNumber(){
        String generateString =RandomStringUtils.randomAlphabetic(3);
        String generatedNum = RandomStringUtils.randomNumeric(3);
        return (generatedNum+generatedNum);
    }

    @AfterClass
    public void tearDown(){
         //driver.quit();
    }
}

