package com.basepage.object;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

/**
 * Created by Jiangwei on 2017/9/20 0020.
 */

public class PlayerPage {
    AndroidDriver driver;
    private ActionTest ac ;
    private  static PlayerPage playerPage;
    public String playMainActivity = "org.stagex.danmaku.player.FungoPlayerActivity";

    public PlayerPage() {
    }

    public PlayerPage(AndroidDriver driver) {
        this.driver = driver;
        getplayPageData();
    }

    //这里需要限制为只可创建唯一实例
    public static PlayerPage getInstance(AndroidDriver driver){
        if(playerPage == null){
            playerPage = new PlayerPage(driver);
        }
        return  playerPage;
    }

    /**
     * 新建一个页面操作类
     * 参数：需要操作页面名字
     */
    public void getplayPageData(){
        ac = new ActionTest(driver,"playPage");
    }

    /**
     * 获得当前activity
     * 参数：无
     */
    public String  getCurrentActivity(){

        return driver.currentActivity();
    }

    /**
     * 物理安静返回上一页
     * 参数：无
     */
    public void goBack(){
        driver.pressKeyCode(AndroidKeyCode.BACK);
    }

    /**
     * 点击视频播放区屏幕中间
     * 参数：无
     */
    public void clickVideoArea(){
        ac.click("video_view");
    }


}
