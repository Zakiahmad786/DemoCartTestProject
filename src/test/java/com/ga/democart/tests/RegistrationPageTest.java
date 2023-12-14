package com.ga.democart.tests;

import com.qa.democart.utils.Constants;
import com.qa.democart.utils.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationPageTest extends BaseTest{

    @BeforeClass
    public void regSetup(){
        regPage=loginPage.navigateToRegisterPage();
    }

    public String getRandomEmail(){
        Random random=new Random();
        String email="automation"+random.nextInt(5000)+"@gmail.com";
        return email;
    }

    @DataProvider
    public Object[][] getRegTestData(){
        return ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
    }
    @Test(dataProvider="getRegTestData")
    public void registrationTest(String firstName,String lastName,String telephone,String password,String subscribe){
        Assert.assertTrue(regPage.accountRegistration(firstName,lastName,getRandomEmail(),telephone,password,subscribe));
    }
}
