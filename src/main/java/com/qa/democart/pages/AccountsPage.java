package com.qa.democart.pages;

import com.qa.democart.utils.Constants;
import com.qa.democart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AccountsPage {
    private WebDriver driver;
    private ElementUtil elementUtil;

    private By accSections= By.cssSelector("div#content h2");
    private By header=By.cssSelector("dive#logo h1 a");
    private By logoutLink=By.linkText("Logout");
    private By searchField=By.name("search");
    private By searchButton=By.cssSelector("dive#search button");
    public AccountsPage(WebDriver driver){
        this.driver=driver;
        elementUtil=new ElementUtil(driver);
    }

    public String getAccPageTitle(){
        return elementUtil.waitForTitleIs(Constants.ACCOUNT_PAGE_TITLE,5);
    }

    public String getAccpageUrl(){
        return elementUtil.waitForUrlContains(Constants.ACCOUNT_PAGE_URL_FRACTION,5);
    }

    public String getAccPageHeader(){
        return elementUtil.doGetText(header);
    }
    public List<String> getAccountSectionsList(){
        List<WebElement> accSecList=elementUtil.getElements(accSections);
        List<String> accSecValueList=new ArrayList<>();
        for (WebElement ele:accSecList){
            accSecValueList.add(ele.getText());
        }
        return accSecValueList;
    }

    public boolean isLogoutLinkExist(){
        return elementUtil.doIsDisplayed(logoutLink);
    }

    public ResultsPage doSearch(String productName){
        elementUtil.doSendKeys(searchField,productName);
        elementUtil.doClick(searchButton);
        return new ResultsPage(driver);
    }
}
