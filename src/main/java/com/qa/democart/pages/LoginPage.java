package com.qa.democart.pages;

import com.qa.democart.utils.Constants;
import com.qa.democart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private By emailId= By.id("input-email");
    private By password=By.id("input-password");
    private By loginBtn=By.xpath("//input[@value='Login']");

    private By forgotPwdLink=By.xpath("//div[@class='form-group']//a[text()='Forgotten Password']");

    private By header=By.cssSelector("div#logo h1 a");
    private By registerLink=By.linkText("Register");
    private WebDriver driver;
    private ElementUtil elementUtil;

    private AccountsPage accPage;
    public LoginPage(WebDriver driver){

        this.driver=driver;
        elementUtil=new ElementUtil(driver);
    }

    public String getLoginPageTitle(){
        return elementUtil.waitForTitleIs(Constants.LOGIN_PAGE_TITLE,5);
    }

    public String getPageHeaderText(){

        return elementUtil.doGetText(header);
    }

    public boolean isForgotPasswordLinkExist(){

        return elementUtil.doIsDisplayed(forgotPwdLink);
    }

    public AccountsPage doLogin(String un,String pwd){
        elementUtil.doSendKeys(emailId,un);
        elementUtil.doSendKeys(password,pwd);
        elementUtil.doClick(loginBtn);
        return new AccountsPage(driver);
    }
    public RegistrationPage navigateToRegisterPage(){
        elementUtil.doClick(registerLink);
        return new RegistrationPage(driver);
    }
}
