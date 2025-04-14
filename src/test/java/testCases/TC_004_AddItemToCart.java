package testCases;

import Utilities.DataProviders;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.*;

public class TC_004_AddItemToCart extends BestTest{

    @Test()
    public void verifyItemAddedToCart(){

        try {

            logger.info("********** Execution Started *************");

            HomePage hp = new HomePage(driver);
            LoginPage lp = new LoginPage(driver);
            TabletsPage tp =new TabletsPage(driver);
            ShoppingCartPage shopping = new ShoppingCartPage(driver);
            MyAccountPage myAccountPage = new MyAccountPage(driver);

            hp.clickOnMyAccount();
            hp.clickOnLogin();

            lp.setEmail(prop.getProperty("email")); //getting data from config.properties file
            Thread.sleep(2000);
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

            logger.info("Click on Tablets");
            hp.clickOnTablets();

            tp.selectItem();
            boolean itemExist = tp.verifyItme();
            Assert.assertEquals(itemExist,true,"Item Not Exist!");

            tp.ClickOnCartTotal();
            logger.info("Clicked On Cart Total Items ...");

            tp.clickOnCheckout();
            logger.info("Clicked on checkout...");


            boolean errorMsg= shopping.verifyErrormsg();
            Assert.assertEquals(errorMsg,true,"successfully added to checkout!");

        }catch (Exception e){
        Assert.fail();
        }
    }
}
