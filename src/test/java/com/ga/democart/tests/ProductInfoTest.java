package com.ga.democart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class ProductInfoTest extends BaseTest{

    @BeforeClass
    public void productInfoPageSetup(){
        accPage=loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
    }

    @Test
    public void productImagesTest(){
        resultsPage=accPage.doSearch("iMac");
        productInfoPage=resultsPage.selectProduct("iMac");
        Assert.assertEquals(productInfoPage.getProductInfo(),3);
    }

    @Test
    public void productInfoTest(){
        resultsPage=accPage.doSearch("Macbook");
        productInfoPage=resultsPage.selectProduct("MacBook Pro");
        Map<String,String> actProductInfoMap=productInfoPage.getProductInfo();
        Assert.assertEquals(actProductInfoMap.get("name"),"MacBook Pro");
        Assert.assertEquals(actProductInfoMap.get("Brand"),"Apple");
        Assert.assertEquals(actProductInfoMap.get("Product Code"),"Product 18");
        Assert.assertEquals(actProductInfoMap.get("price"),"$2,000.00");
    }
}
