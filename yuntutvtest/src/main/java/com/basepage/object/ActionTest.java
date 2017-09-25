package com.basepage.object;

import com.appium.api.ElementMethodObject;
import com.appium.api.Log;
import com.appium.api.SwipeMethodObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

/**
 * Created by Jiangwei on 2017/9/11 0011.
 * 提供动作类操作，点击设置值等，并完成元素初始化
 * 继承 InitTestPage类
 */

public class ActionTest extends InitTestPage{
    public AndroidDriver driver;
    public String pageName;
    ElementMethodObject emb=new ElementMethodObject();
    SwipeMethodObject sm;

    public ActionTest(AndroidDriver driver){
        this.driver=driver;
    }

    public ActionTest(AndroidDriver driver,String pageName){
        super(driver,pageName);
        this.driver=driver;
        this.pageName=pageName;
        sm=new SwipeMethodObject(driver);
    }

    /**
     * 给某个element 输入字符
     * 参数：buttonName，value
     */
    public void setValue(String buttonName,String value){
        WebElement element =emb.waitForElementPresent(driver,getBy(buttonName));
        int size = element.getText().length();
        driver.pressKeyCode(123);
        for(int i = 0; i < size; i++){
            Log.info("搜索框有内容，删除原来数据...");
            driver.pressKeyCode(AndroidKeyCode.BACKSPACE);
        }
        element.sendKeys(value);

    }

    /**
     * 获取id的text内容
     * 参数：buttonName
     */
    public String getValue(String buttonName){
        WebElement element =emb.waitForElementPresent(driver,getBy(buttonName));
        String value = element.getText();
        return value;
    }

    /**
     * 点击element
     * 参数：buttonName
     */
    public void click(String buttonName){
        emb.waitForElementPresent(driver,getBy(buttonName)).click();
    }
    
    /**
     * 点击屏幕（基于element）
     * 参数：buttonName
     */
    public void tap(String buttonName){
        TouchAction tap=new TouchAction(driver);
        tap.tap(emb.waitForElementPresent(driver,getBy(buttonName))).release().perform();
    }

    /**
     * 获取元素列表
     * 参数：buttonName
     */
    public List<WebElement>  getElementList(String buttonName){
        List<WebElement> elements = (List<WebElement>)driver.findElements(getBy(buttonName));
        return  elements;
    }

    /**
     * 滑动屏幕，这里滑动速度固定未2.5s，滑动次数 1次
     * 参数：direction 滑动方向
     */
    public void swipeScreen(String direction){
        switch (direction) {
            case "up":
                Log.info("开始向上滑动...");
                sm.slideUP(driver,2500,1);
                break;
            case "down":
                Log.info("开始向下滑动...");
                sm.slideDown(driver,2500,1);
                break;
            case "right":
                Log.info("开始向右滑动...");
                sm.slideRight(driver,2500,1);
                break;
            case "left":
                Log.info("开始向左滑动...");
                sm.slideLeft(driver,2500,1);
                break;
            default:break;

        }
    }

    /**
     * 滑动某个元素，左右滑动
     * 参数：buttonName
     */
    public void swipElement(String buttonName,String direction){
        WebElement element =emb.waitForElementPresent(driver,By.xpath(buttonName));
        sm.slidingElement(element,direction,1500,1);
    }


    /**
     * 判断某个元素是否存在
     * 参数：buttonName
     */
    public boolean isElementExist(String buttonName){
        boolean exist = false;
        if(emb.isElementPresent(driver,getBy(buttonName))){
            exist = true;
            Log.info("需要找的元素存在，返回True...");
        }
        return exist;
    }

    /**
     * 在元素上长按
     * 参数：buttonName
     */
    public void loogPress(String buttonName){
        try{
            WebElement element = emb.waitForElementPresent(driver,getBy(buttonName));
            TouchAction ta = new TouchAction(driver);
            ta.longPress(element).release().perform();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
