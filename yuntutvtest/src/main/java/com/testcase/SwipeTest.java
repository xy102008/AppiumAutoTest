package com.testcase;


import com.appium.api.Log;
import com.basepage.object.HomePage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

import static com.appium.api.ThreadSleepMethod.threadSleep;

/**
 * Created by Jiangwei on 2017/8/29 0029.
 */

public class SwipeTest {
    AndroidDriver<?> driver ;
    HomePage homePage;

    @BeforeClass
    public void setDriver(){
        this.driver = Utils.driver;
        homePage=new HomePage(driver);
    }

    /**
     * 滑动banner
     */
    @Test(description="滑动Banner")
    public void swipBannerTest(){
        homePage.closeAD();
        for(int i=0;i<2;i++){
            homePage.swipeBanner("Left");
            threadSleep(3000);
        }
    }

    /**
     * 向下滑动
     */
  //  @Test(description="滑动测试")
    public void swipeTest(){
        threadSleep(15000);
        Log.info("滑动测试开始...");
        homePage.closeAD();
        for(int i=0;i<3;i++){
            homePage.swipeTest("up");
            threadSleep(3000);
        }
    }


}

