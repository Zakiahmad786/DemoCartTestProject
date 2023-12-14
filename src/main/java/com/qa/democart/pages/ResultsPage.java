package com.qa.democart.pages;

import com.qa.democart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ResultsPage {
    private WebDriver driver;
    private ElementUtil elementUtil;

    private By searchHeader= By.cssSelector("div#content h1");
    private By productResults=By.cssSelector("div.caption a");
    public ResultsPage(WebDriver driver){
        this.driver=driver;
        elementUtil=new ElementUtil(driver);
    }

    public String getSearchPageHeader(){
        return elementUtil.doGetText(searchHeader);
    }

    public int getSearchProductListsCount(){
       return elementUtil.getElements(productResults).size();
    }

    public ProductInfoPage selectProduct(String mainProductName){
       List<WebElement> searchList=elementUtil.getElements(productResults);
       for (WebElement ele:searchList){
           if (ele.getText().trim().equals(mainProductName)){
               ele.click();
               break;
           }
       }
       return new ProductInfoPage(driver);
    }
}
