package pageObjects;

import org.apache.xmlbeans.impl.common.XPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

    public AccountRegistrationPage(WebDriver driver){
        super(driver);
    }

    @FindBy (xpath="//input[@id='input-firstname']")
    private WebElement txtFirstName;

    @FindBy (xpath = "//input[@id='input-lastname']")
    private WebElement txtLastName;

    @FindBy (xpath = "//input[@id='input-email']")
    private WebElement txtEmail;

    @FindBy (xpath = "//input[@id='input-telephone']")
    private WebElement txtTelePhone;

    @FindBy (xpath = "//input[@id='input-password']")
    private WebElement txtPass;

    @FindBy (xpath = "//input[@id='input-confirm']")
    private WebElement txtConfirmPass;

    @FindBy (xpath = "//input[@type='radio' and @value='0']")
    private WebElement txtSubscribe;

    @FindBy (xpath = "//input[@name='agree']")
    private WebElement chkPolicyAgree;

    @FindBy (xpath = "//input[@type='submit']")
    private WebElement btnSubmit;

    @FindBy (xpath = "//div[@id='content']//child::h1")
    private WebElement confMsg;

    public void enterFirstName(String firstName){
        txtFirstName.sendKeys(firstName);
    }
    public void enterLastName(String lastName){
        txtLastName.sendKeys(lastName);
    }
    public void enterEmail(String email){
        txtEmail.sendKeys(email);
    }
    public void enterTelePhone(String telephone){
        txtTelePhone.sendKeys(telephone);
    }
    public void enterPass(String pass){
        txtPass.sendKeys(pass);
    }
    public void enterConfirmPass(String cfmPass){
        txtConfirmPass.sendKeys(cfmPass);
    }
    public void clickOnPolicyAgree(){
        chkPolicyAgree.click();
    }

    public void clickOnContinue(){
        btnSubmit.click();
    }

    public String getConfirmationMsg(){
        try {
            return (confMsg.getText());
        }catch (Exception e){
            return(e.getMessage());
        }
    }
}
