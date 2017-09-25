package com.testcase;

import com.appium.api.Log;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * Created by Administrator on 2017/9/18 0018.
 */

public class BaseTest extends Utils{



    @BeforeSuite
    public void beforeSuit(){
        creatDriver();
    }



    @AfterSuite
    public  void tearDown() throws Exception {
        //测试完毕，关闭driver，不关闭将会导致会话还存在，下次启动就会报错
        Log.info("测试完成...退出app");
        driver.quit();
    }

}
