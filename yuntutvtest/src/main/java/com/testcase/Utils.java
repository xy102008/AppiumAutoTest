package com.testcase;

import com.appium.api.Log;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

/**
 * Created by Jiangwei on 2017/9/13 0013.
 */

public class Utils {

    public static AndroidDriver<?> driver;


    public void creatDriver() {
        File classpathRoot = new File(System.getProperty("user.dir"));
        //app的目录
        File appDir = new File(classpathRoot, "/apps");
        //app的名字，对应你apps目录下的文件
        // File app = new File(appDir, "hotchat_1.5.0_150_jiagu_sign.apk_1.5.0.apk");
        //创建Capabilities
        DesiredCapabilities dc = new DesiredCapabilities();
        //设置要调试的模拟器的名字
        dc.setCapability("deviceName", "127.0.0.1:62001");
        //support Chinese支持中文输入
        dc.setCapability("unicodeKeyboard", "True");

        //重置输入法为系统默认
        dc.setCapability("resetKeyboard", "True");

        //设置模拟器的系统版本
        dc.setCapability("platformVersion", "5.1.1");

        dc.setCapability("noReset", true); //不需要再次安装

        //no need sign 安装时不对apk进行重签名，设置很有必要，否则有的apk在重签名之后无法正常使用
        dc.setCapability("noSign", "True");
        //设置app的包名
        dc.setCapability("appPackage", "org.fungo.fungolive");
        dc.setCapability("sessionOverride", true);
        //设置app的启动activity
        dc.setCapability("appActivity", "org.stagex.danmaku.activity.SplashActivity");

        //启动driver
        try {
            driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
            Log.info("创建driver对象成功...");
        } catch (Exception e) {
            Log.error("创建driver对象失败...");
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        Log.info("App is launched!....");
    }




}

