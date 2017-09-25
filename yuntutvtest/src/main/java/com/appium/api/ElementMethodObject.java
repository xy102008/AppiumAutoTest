package com.appium.api;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
/**
 * Created by Jiangwei on 2017/9/5 0005.
 * 说明：查找元素、判断元素是否存在
 */

public class ElementMethodObject {

     /**
     * 判断元素是否存在
     * 参数： androiddriver By的对象
      * return 布尔值
     **/

    public  boolean isElementPresent(AndroidDriver driver, By by){
        try {
            if(driver==null){
                Log.info("driver 对象为空...请检查代码....");
            }
            driver.findElement(by);
            Log.info("在当前页面找到元素："+by.toString());
            return true;
        }catch (NoSuchElementException e){
            //e.printStackTrace();
            Log.error("在当前页面找不到该元素："+by.toString());
            return false;
        }
    }

    /**
     * 等待元素出现 10s超时，找不到返回null 自定义方法等待
     * 参数： androiddriver By的对象 ，
     * 找到返回true
     **/
    public WebElement waitForElementPresent(AndroidDriver driver, By by){
        WebElement webElement = null;
        for(int sec=0;;sec++){
            if(sec >= 10) throw new NoSuchElementException("超过10s元素未找到："+by.toString());
            if(isElementPresent(driver,by)) {
                webElement = driver.findElement(by);
                break;
            }
            Log.info("继续尝试查找："+by.toString());
            ThreadSleepMethod.threadSleep(1000);
        }
        return webElement;
    }

    /**
     * 查找元素 显性等待
     * 参数：driver 对象,等待时间,by对象
     * return  webelment对象
     **/
    public WebElement WebElementWait(AndroidDriver driver , int waittime, final By by){
        WebDriverWait wait = new WebDriverWait(driver, waittime);
        WebElement element = wait.until(new ExpectedCondition<WebElement>(){
            @Override
            public WebElement apply(WebDriver d) {
                return
                        d.findElement(by);
            }});
        return element;
    }
}
