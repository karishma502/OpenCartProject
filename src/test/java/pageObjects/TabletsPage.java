package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TabletsPage extends BasePage{

    public TabletsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@id='content']//div[@class='row']//div[@class='caption']//h4//a")
    private WebElement item1;

    @FindBy(xpath = "//div[@id='content']//div[@class='col-sm-4']//h1")
    private WebElement itemHeading;

    @FindBy(xpath = "//button[@id='button-cart']")
    private WebElement btnCart;

    @FindBy(xpath = "//span[@id='cart-total']")
    private WebElement btnCartTotal;

    @FindBy(xpath = "//div[@id='cart']//ul//li[2]//p//a[2]//strong[contains(text(),'Checkout')]")
    private WebElement btnCheckout;




    public void selectItem(){
        item1.click();
    }

    public boolean verifyItme() {
        try {
            itemHeading.isDisplayed();
        } catch (Exception e) {
            return false;

        }
        return true;
    }
    public void addItemToCart(){
        btnCart.click();
    }


    public void ClickOnCartTotal(){
        btnCartTotal.click();
    }

    public void clickOnCheckout(){
        btnCheckout.click();
    }

}
