package testCases;

import Utilities.ExtentReportManager;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
@Listeners(ExtentReportManager.class)
public class TC__002_LoginTest extends BestTest{

    @Test(groups = {"Sanity","Master"})
    public void Verify_Login(){
        logger.info("***** Starts the Execution *****");

        try {
            HomePage hp = new HomePage(driver);
            LoginPage lp = new LoginPage(driver);
            MyAccountPage myAccountPage = new MyAccountPage(driver);

            hp.clickOnMyAccount();
            hp.clickOnLogin();

            lp.setEmail(prop.getProperty("email")); //getting data from config.properties file
            lp.setPassword(prop.getProperty("password")); //getting data from config.properties file
            try {
                lp.clickOnLoginBtn();
                logger.info("clicked on Login");
            }catch (Exception e) {
                logger.info("Failed to click login");
                logger.debug("Failed debug");

            }

            boolean myHeading = myAccountPage.isMyAccountExist();
            Assert.assertEquals(myHeading, true, "Login Failed!");
        }
        catch (Exception e){
            Assert.fail();
        }
    }
}
