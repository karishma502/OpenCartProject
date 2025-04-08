package pageObjects;

import net.bytebuddy.implementation.bind.annotation.Super;
import org.apache.xmlbeans.impl.common.XPath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver){
         super(driver);
    }

    @FindBy (xpath ="//div[@id ='top-links']//a[normalize-space()='Register']")
    private WebElement lnkRegister;

    @FindBy (xpath ="//div[@id ='top-links']//a[normalize-space()='Login']")
    private WebElement lnkLogin;

    @FindBy (xpath ="//div[@id ='top-links']//a[normalize-space()='My Account']")
    private WebElement lnkMyAccount;

    @FindBy (xpath ="//div[@id ='top-links']//a[normalize-space()='Logout']")
    private WebElement lnkLogout;


    public void clickOnRegister(){
        lnkRegister.click();
    }
    public void clickOnLogin(){
        lnkLogin.click();
    }
    public void clickOnMyAccount(){
        lnkMyAccount.click();
    }
    public void ClickOnLogout(){
        lnkLogout.click();
    }

}
