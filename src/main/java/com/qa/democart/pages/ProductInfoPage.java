package com.qa.democart.pages;

import com.qa.democart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ProductInfoPage {

    private WebDriver driver;
    ElementUtil elementUtil;

    private By productHeader= By.cssSelector("dive#content h1");
    private By productImages=By.cssSelector("ul.thumbnails img");
    private By productMetaData=By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
    private By productPriceData=By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
    private By quantity=By.id("input-quantity");
    private By addToCartBtn=By.id("button-cart");

    private Map<String,String> productInfoMap;
    public ProductInfoPage(WebDriver driver){
        this.driver=driver;
        elementUtil=new ElementUtil(driver);
    }
    public String getProductHeaderText(){
        return elementUtil.doGetText(productHeader);
    }
    public int getProductImagesCount(){
        return elementUtil.getElements(productImages).size();
    }
    public Map<String, String> getProductInfo(){
        productInfoMap=new HashMap<String,String>();
        productInfoMap.put("nam",getProductHeaderText());

        List<WebElement> metaDataList=elementUtil.getElements(productMetaData);
        // Meta data:
        // Brand: Apple
        //Product Code: Product 18
        //Reward Points: 800
        //Availability: Out Of Stock

        for (WebElement e:metaDataList){
            String[] meta=e.getText().split(":");
            String metaKey=meta[0].trim();
            String metaValue=meta[1].trim();
            productInfoMap.put(metaKey,metaValue);
        }

        //price data
        List<WebElement> priceList=elementUtil.getElements(productPriceData);
        String price=priceList.get(0).getText().trim();
        String exTaxPrice=priceList.get(1).getText().trim();

        productInfoMap.put("price",price);
        productInfoMap.put("ExTaxPrice",exTaxPrice);

        return productInfoMap;
    }


}
