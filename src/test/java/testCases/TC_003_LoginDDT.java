package testCases;

import org.openqa.selenium.devtools.v85.layertree.model.StickyPositionConstraint;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC_003_LoginDDT extends BestTest{

    @Test(dataProvider = "LoginDetails")
    public void verifyLoginDDT(String email, String pass, String exp){
        logger.info("***** Starts the Execution *****");

        try {
            HomePage hp = new HomePage(driver);
            LoginPage lp = new LoginPage(driver);
            MyAccountPage myAccountPage = new MyAccountPage(driver);

            hp.clickOnMyAccount();
            hp.clickOnLogin();

            lp.setEmail(email); //getting data from excel file
            lp.setPassword(pass); //getting data from excel file
            try {
                lp.clickOnLoginBtn();
                logger.info("clicked on Login");
            } catch (Exception e) {
                logger.info("Failed to click login");
                logger.debug("Failed debug");

            }
            boolean myHeading = myAccountPage.isMyAccountExist();

            if(exp.equalsIgnoreCase("valid")){
                if(myHeading==true){
                    Assert.assertTrue(true);
                    hp.ClickOnLogout();
                }
            }
            else {
                Assert.assertTrue(false);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
