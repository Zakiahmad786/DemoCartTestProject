package com.qa.democart.utils;

import com.qa.democart.factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ElementUtil {
    WebDriver driver;
    private JavaScriptUtil javaScriptUtil;
    public ElementUtil(WebDriver driver){
        this.driver=driver;
        javaScriptUtil=new JavaScriptUtil(driver);
    }

    public WebElement getElement(By locator){
        WebElement element=driver.findElement(locator);
        if(Boolean.parseBoolean(DriverFactory.highlight)){
            javaScriptUtil.flash(element);
        }
        return element;
    }
    public void doSendKeys(By locator,String value){
        WebElement element=getElement(locator);
        element.clear();
        element.sendKeys(value);
    }

    public void doClick(By locator){
        getElement(locator).click();
    }

    public String doGetText(By locator){
        return getElement(locator).getText();
    }

    public String doGetAttribute(By locator,String attrName){
        return getElement(locator).getAttribute(attrName);
    }

    public boolean doIsDisplayed(By locator){
        return getElement(locator).isDisplayed();
    }
    public String waitForTitleIs(String fullTitle, int timeOut){
        WebDriverWait wait=new WebDriverWait(driver,timeOut);
        if(wait.until(ExpectedConditions.titleIs(fullTitle))){
            return driver.getTitle();
        }
        return null;
    }

    public String waitForUrlContains(String urlFaction, int timeout){
        WebDriverWait wait=new WebDriverWait(driver,timeout);
        if(wait.until(ExpectedConditions.urlContains(urlFaction)))
            return driver.getCurrentUrl();
        return null;
    }
    public List<WebElement> getElements(By locator){
        return driver.findElements(locator);
    }

    public WebElement waitForElementPresence(By locator,int timeOut){
        WebDriverWait wait=new WebDriverWait(driver,timeOut);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
