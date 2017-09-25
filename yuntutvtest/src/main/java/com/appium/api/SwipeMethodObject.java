package com.appium.api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

/**
 * Created by Jiangwei on 2017/9/5 0005.
 * 提供滑动屏幕和元素的方法
 */

public class SwipeMethodObject {

    public AndroidDriver driver;

    public SwipeMethodObject(AndroidDriver driver){
        this.driver = driver;
    }

    /**
     * 获取屏幕的尺寸
     * 参数：driver对象
     * return  屏幕宽和高
     **/
    public int[] getAppScreen(AndroidDriver driver){
        int widthScreen=driver.manage().window().getSize().getWidth();
        int heightScreen = driver.manage().window().getSize().getHeight();
        int[] appWidthAndHeight={widthScreen,heightScreen };
        return appWidthAndHeight;
    }

    /**
     * 向上滑动
     * 参数：duration 持续时间毫秒单位即滑动速度，num滑动次数
     * return  无
     **/
    public void slideUP(AndroidDriver driver,int duration,int num) {
        int startX = this.getAppScreen(driver)[0]*1/2;
        int startY = this.getAppScreen(driver)[1]*4/5;
        int endY = this.getAppScreen(driver)[1]*2/5;
        try{
            for(int i=0; i<num; i++){
                driver.swipe(startX, startY, startX, endY, duration);
                ThreadSleepMethod.threadSleep(1000);
            }
            Log.info("滑动成功...");

        }catch (Exception e){
            Log.error("滑动失败");
            e.printStackTrace();
        }

    }

    /**
     * 向下滑动
     * 参数：duration 持续时间毫秒单位即滑动速度，num滑动次数
     * return  无
     **/
    public void slideDown(AndroidDriver driver,int duration,int num) {

        int startX = this.getAppScreen(driver)[0]*1/2;
        int startY = this.getAppScreen(driver)[1]*1/5;
        int endY = this.getAppScreen(driver)[1]*4/5;
        try{
            for (int i=0; i<num; i++){
                driver.swipe(startX, startY, startX, endY, duration);
                ThreadSleepMethod.threadSleep(1000);
            }
            Log.info("滑动成功...");
        }catch (Exception e){
            Log.info("滑动失败...");
            e.printStackTrace();
        }

    }

    /**
     * 向左滑动
     * 参数：duration 持续时间毫秒单位即滑动速度，num滑动次数
     * return  无
     **/
    public void slideLeft(AndroidDriver driver,int duration,int num) {

        int startX = this.getAppScreen(driver)[0]*4/5;
        int endX = this.getAppScreen(driver)[0]*1/5;
        int startY = this.getAppScreen(driver)[1]*1/2;
        try{
            for (int i=0; i<num; i++){
                driver.swipe(startX, startY, endX, startY, duration);
                ThreadSleepMethod.threadSleep(1000);
            }
            Log.info("滑动成功...");
        }catch (Exception e){
            Log.info("滑动失败...");
            e.printStackTrace();
        }
    }

    /**
     * 向右滑动
     * 参数：duration 持续时间毫秒单位即滑动速度，num滑动次数
     * return  无
     **/
    public void slideRight(AndroidDriver driver,int duration,int num) {

        int startX = this.getAppScreen(driver)[0]*1/5;
        int endX = this.getAppScreen(driver)[0]*4/5;
        int startY = this.getAppScreen(driver)[1]*1/2;
        try{
            for (int i=0; i<num; i++){
                driver.swipe(startX, startY, endX, startY, duration);
                Thread.sleep(1000);
            }
            Log.info("滑动成功...");
        }catch (Exception e){
            Log.error("滑动失败...");
            e.printStackTrace();
        }
    }

    /**
     * 滑动指定元素
     * 参数：duration 持续时间毫秒单位即滑动速度，num滑动次数
     * return  无
     **/
    public void slidingElement(WebElement element,String direction,int duration, int num){
        //XY为元素起点坐标
        int x = element.getLocation().getX();
        int y = element.getLocation().getY();
        //获取元素高和宽
        int elementWidth = element.getSize().getWidth();
        int elementHeight = element.getSize().getHeight();
        switch (direction){
            case "Left":
                int starX =x+elementWidth*3/4;
                int endX =x+elementWidth/4;
                int startY =elementHeight/2+y;
                int endY =elementHeight/2+y;
                for(int i=0; i<num; i++){
                    driver.swipe(starX, startY, x, endY, duration);
                }
                break;
            case "Right":
                int rRtarX =x+elementWidth/4;
                int rEndX =x+elementWidth*3/4;
                int RStartY =elementHeight/2+y;
                int REndY =elementHeight/2+y;
                for(int i=0; i<num; i++){
                    driver.swipe(rRtarX, RStartY, rEndX, REndY, duration);
                }
                break;
            default:break;

        }

    }


    /**
     * 手势解锁九宫格
     * 0 1 2 3 4 5 6 7 8
     */
    public void swipeToUnlock(AndroidDriver driver, WebElement lockImageView, int[] path) {
        TouchAction touchAction = new TouchAction(driver);
        List<WebElement> lockItems = lockImageView.findElements(By.className("android.view.View"));
        for (int i = 0; i < path.length; i++) {
            if (i == 0) {
                touchAction.press(lockItems.get(path[i])).moveTo(lockItems.get(path[i]));
            } else {
                touchAction.moveTo(lockItems.get(path[i]));
            }
        }
        touchAction.release();
        touchAction.perform();
    }

}
