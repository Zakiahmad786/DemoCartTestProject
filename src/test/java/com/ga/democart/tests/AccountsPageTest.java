package com.ga.democart.tests;

import com.qa.democart.pages.AccountsPage;
import com.qa.democart.utils.Constants;
import com.qa.democart.utils.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class AccountsPageTest extends BaseTest{

    @BeforeClass
    public void doLogin(){
        accPage=loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
    }

    @Test(priority = 1)
    public void accPageTitleTest(){
        String title=accPage.getAccPageTitle();
        Assert.assertEquals(title, Constants.ACCOUNT_PAGE_TITLE);
    }

    @Test(priority = 2)
    public void accPageHeaderTest(){
        String header=accPage.getAccPageHeader();
        Assert.assertEquals(header,Constants.PAGE_HEADER);
    }

    @Test(priority = 3)
    public void accSectionsListTest(){
        List<String> actAccSectionsList=accPage.getAccountSectionsList();
        Assert.assertEquals(actAccSectionsList,Constants.EXPECTED_ACC_SEC_LIST);
    }

    @Test(priority = 4)
    public void logoutLinkExistTest(){
        Assert.assertTrue(accPage.isLogoutLinkExist());
    }

    @DataProvider
    public Object[][] getSearchData(){
        return new Object[][]{{"Macbook Pro"},{"Macbook Air"},{"Apple"}};
    }
    @Test(priority = 5,dataProvider = "getSearchData")
    public void searchTest(String productName){
        resultsPage=accPage.doSearch(productName);
        String resultHeader=resultsPage.getSearchPageHeader();
        Assert.assertTrue(resultHeader.contains(productName));
    }

   /* @DataProvider
    public Object[][] getProductSelectData(){
        return new Object[][] {{"Macbook","MacBook Air"},{"Macbook","MacBook Pro"},{"Apple","Apple Cinema 30\""}};
    }*/
    @DataProvider
    public Object[][] getProductSelectData(){
        return ExcelUtil.getTestData(Constants.SEARCH_SHEET_NAME);
    }

    @Test(priority = 6,dataProvider="getProductSelectData")
    public void selectProductTest(String productName,String mainProductName){
        resultsPage=accPage.doSearch(productName);
        productInfoPage=resultsPage.selectProduct(mainProductName);
        String headerPage=productInfoPage.getProductHeaderText();
        Assert.assertEquals(headerPage,mainProductName);
    }
}
