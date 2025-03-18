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

    @FindBy (xpath ="//div[@class='navbar-right hidden-xs']//a[contains(text(),'Register')]")
    private WebElement lnkRegister;

    @FindBy (xpath ="//div[@class='navbar-right hidden-xs']//a[contains(text(),'Login')]")
    private WebElement lnkLogin;



    public void clickOnRegister(){
        lnkRegister.click();
    }
    public void clickOnLogin(){
        lnkLogin.click();
    }
}
