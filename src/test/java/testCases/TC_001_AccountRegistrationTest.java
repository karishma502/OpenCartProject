package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC_001_AccountRegistrationTest extends BestTest {

    @Test(groups = {"Regression","Master"})
    public void verify_Account_Registry() throws InterruptedException {
        logger.info("******* Started AccountRegistrationTest ******");

        try {
            HomePage hp = new HomePage(driver);
            AccountRegistrationPage ar = new AccountRegistrationPage(driver);

            hp.clickOnMyAccount();
            logger.info("Click on My account");
            hp.clickOnRegister();
            logger.info("Click on Register account");

            //call the functions for the fill the registration form

            ar.enterFirstName(randomString().toUpperCase());
            Thread.sleep(2000);
            ar.enterLastName(randomString().toUpperCase());
            ar.enterEmail(randomString()+"@gmail.com");
            ar.enterTelePhone(prop.getProperty("telephone")); //getting value from config.properties file
            String password = randomAlphaNumber();
            ar.enterPass(password);
            ar.enterConfirmPass(password);
            ar.clickOnPolicyAgree();
            ar.clickOnContinue();
            Thread.sleep(2000);
            String confmsf = ar.getConfirmationMsg();
            Thread.sleep(2000);
            if(confmsf.equals("Your Account Has Been Created!!")){
                Assert.assertTrue(true);
            }
            else {
                logger.error("Test Case Failed");
                logger.debug("Logging debug");
                Assert.assertFalse(false);
            }
        }
        catch (Exception e){
            Assert.fail();
        }

        logger.info("******* Finished AccountRegistrationTest ******");
    }

}


