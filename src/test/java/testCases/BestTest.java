package testCases;

import Utilities.DataProviders;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BestTest {

   public static WebDriver driver;
   public Logger logger; //Log4j2
   public Properties prop; //properties file
   public FileInputStream fis;
    @BeforeClass(groups = {"Sanity","Regression","Master"})
    @Parameters({"browser"})
    public void setUp(String br) throws IOException {

        //Specifiy the location of property file
        File src = new File("./src/test/resources/config.properties");
        //Create a FileinputStram class object to load the file
        fis = new FileInputStream(src);
        //Create a properties class object to read properties files
        prop = new Properties();
        prop.load(fis);

        //LogManager class with getLogger method to get in logger instance
        logger = LogManager.getLogger(this.getClass());

        //Set up browser
        switch (br.toLowerCase()){
            case "chrome" :
                System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
                driver=new ChromeDriver();
            break;
            case  "edge" : driver= new EdgeDriver();
            break;
            case  "firefox" : driver=new FirefoxDriver();
            break;
            default: System.out.println("Invalid browser");
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(prop.getProperty("url"));
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

    @AfterClass(groups = {"Sanity","Regression","Master"})
    public void tearDown(){
         //driver.quit();
    }

    // capture screenshot method
    public String captureScreenshot(String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String screenshotName = testName + "-" + timestamp + ".png";
        String destPath = "screenshots/" + screenshotName;

        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destination = new File(System.getProperty("user.dir") + "/" + destPath);
        //destination.getParentFile().mkdirs(); // make dirs if needed
        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return destPath; // This is RELATIVE for Extent to read
    }

}

