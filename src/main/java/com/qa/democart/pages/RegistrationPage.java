package com.qa.democart.pages;

import com.qa.democart.utils.Constants;
import com.qa.democart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    private By firstName= By.id("input-firstname");
    private By lastName=By.id("input-lastname");
    private By email=By.id("input-email");
    private By telephone=By.id("input-telephone");
    private By password=By.id("input-password");
    private By confirmPassword=By.id("input-confirm");

    private By subscribeYes=By.xpath("(//label[@class='radio-inline'])[position()=1]/input");
    private By subscribeNo=By.xpath("(//label[@class='radio-inline'])[position()=2]/input");

    private By agreeCheckBox=By.name("agree");
    private By continueButton=By.xpath("//input[@type='submit' and @value='Continue']");
    private By successMsg=By.cssSelector("div#content h1");
    private By registerLink=By.linkText("Register");

    private By logoutLink=By.linkText("Logout");
    public RegistrationPage(WebDriver driver){
        this.driver=driver;
        elementUtil=new ElementUtil(driver);
    }

    public boolean accountRegistration(String firstName,String lastName,String email,String telephone,String password,String subscribe){

        elementUtil.doSendKeys(this.firstName,firstName);
        elementUtil.doSendKeys(this.lastName,lastName);
        elementUtil.doSendKeys(this.email,email);
        elementUtil.doSendKeys(this.telephone,telephone);
        elementUtil.doSendKeys(this.password,password);
        elementUtil.doSendKeys(this.confirmPassword,password);

        if(subscribe.equalsIgnoreCase("yes")){
            elementUtil.doClick(subscribeYes);
        }
        else{
            elementUtil.doClick(subscribeNo);
        }
        elementUtil.doClick(agreeCheckBox);
        elementUtil.doClick(continueButton);

        String msg= elementUtil.waitForElementPresence(successMsg, Constants.DEFAULT_TIME_OUT).getText();
        if(msg.contains(Constants.REGISTER_SUCCESS_MESSG)){
            elementUtil.doClick(logoutLink);
            elementUtil.doClick(registerLink);
            return true;
        }
        return false;
    }
}
