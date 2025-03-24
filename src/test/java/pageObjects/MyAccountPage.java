package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@id='content']//h2[starts-with(text(),'My Account')]")
    private WebElement msgHeading;

    public boolean isMyAccountExist(){
        try {
            return (msgHeading.isDisplayed());
        }
        catch (Exception e){
            return false;
        }
    }
}
