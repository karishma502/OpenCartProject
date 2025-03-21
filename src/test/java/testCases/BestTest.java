package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BestTest {

   public WebDriver driver;
   public Logger logger;
    @BeforeClass
    @Parameters({"browser"})
    public void setUp(String br){
        //LogManager class with getLogger method to get in logger instance
        logger = LogManager.getLogger(this.getClass());
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        switch (br.toLowerCase()){
            case "chrome" : driver=new ChromeDriver();
            break;
            case  "edge" : driver= new EdgeDriver();
            break;
            case  "firefox" : driver=new FirefoxDriver();
            break;
            default: System.out.println("Invalid browser");
        }

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

