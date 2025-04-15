package pageObjects;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SeleniumGrid {

    @DataProvider(parallel = true)
    public Object[][] sendData(){

        Object arr[][] =new Object[2][2];

        arr[0][0] ="Email_1";
        arr[0][1]="chrome";

        arr[1][0] ="Email_1";
        arr[1][1]="chrome";

        return arr;
    }

    @Test(dataProvider = "sendData")
    public void openBrowser(String userName,String browseName) throws MalformedURLException, URISyntaxException {

        ChromeOptions options= new ChromeOptions();

        if(browseName.equalsIgnoreCase("chrome")) {
            options.addArguments("--start-maximized");
            options.addArguments("disable-infobars");
            options.addArguments("--no-sandbox");
            options.setAcceptInsecureCerts(true);
            options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);

            URL gridUrl = new URI("http://localhost:4444/wd/hub").toURL();
            RemoteWebDriver driver = new RemoteWebDriver(gridUrl, options);
            try {
                driver.get("https://www.facebook.com/");
                driver.findElement(By.id("email")).sendKeys(userName);
            } finally {
                driver.quit(); // Ensure the browser is closed after the test
            }

        }
    }
}
