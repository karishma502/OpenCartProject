package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends BasePage{
    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@id='checkout-cart']//div/text()[contains(., 'Products marked with ***')]")
    private WebElement errorMsg;

    public boolean verifyErrormsg(){
        try{
            errorMsg.isDisplayed();
        }catch (Exception e){
            return false;
        }
        return false;
    }
}
