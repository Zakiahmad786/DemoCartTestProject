package com.ga.democart.tests;

import com.qa.democart.pages.AccountsPage;
import com.qa.democart.utils.Constants;
import com.qa.democart.utils.Errors;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest{

    @Test(priority=1)
    public void loginPageTitleTest(){
        String pageTitle= loginPage.getLoginPageTitle();
        System.out.println("Login Page title is: "+pageTitle);
        Assert.assertEquals(pageTitle, Constants.LOGIN_PAGE_TITLE, Errors.TITLE_ERROR_MESSG);
    }

    @Test(priority=2)
    public void loginPageHeaderTest(){
         String header=loginPage.getPageHeaderText();
        System.out.println("Header Page text is: "+header);
        Assert.assertEquals(header,Constants.PAGE_HEADER,Errors.HEADER_ERROR_MESSG);
    }

    @Test(priority=3)
    public void forgotPwdLinkTest(){
        Assert.assertTrue(loginPage.isForgotPasswordLinkExist());
    }

    @Test(priority = 4)
    public void loginTest(){

        AccountsPage accPage=loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
        Assert.assertTrue(accPage.isLogoutLinkExist());
    }
}
