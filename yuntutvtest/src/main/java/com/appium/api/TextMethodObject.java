package com.appium.api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

/**
 * Created by Jiangwei on 2017/9/5 0005.
 * 该settext 方法可忽略，不用封装
 */

public class TextMethodObject {

    /**
     * Created by Administrator on 2017/7/28 0028.
     * Text输入
     * 参数： WebElement 对象和text内容
     **/
    public void setText(WebElement elementEdit, String text){
        elementEdit.sendKeys(text);
    }

    /**
     * Created by Administrator on 2017/7/28 0028.
     * Text获取
     * 参数： WebElement 对象和text内容
     **/
    public String  getText(WebElement elementEdit){
        return elementEdit.getText();
    }

    /**
     * Created by Administrator on 2017/7/28 0028.
     * 清除输入
     * 参数： WebElement
     **/
    public void clearText(WebElement elementEdit){
        elementEdit.clear();
    }


    /**
     * Created by Administrator on 2017/7/28 0028.
     * 长按操作
     * 参数： WebElement 对象driver
     **/
    public void longPressByID(AndroidDriver driver, WebElement items){
        TouchAction tAction=new TouchAction(driver);
        tAction.longPress(items).perform();

    }

    /**
     * toast 获取
     * 参数：
     * 暂未验证，需要升级appium
     **/
    public  boolean getToast(AndroidDriver driver, String toast) {
        try {
            final WebDriverWait wait = new WebDriverWait(driver, 1);
            if (wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[contains(@text,'"+toast+"')]")))!=null) {
                Log.info("找到了toast");
                return true;
            }
            return false;
        } catch (Exception e) {
            Log.error("找不到toast:" + toast);
            return false;
        }
    }

}
