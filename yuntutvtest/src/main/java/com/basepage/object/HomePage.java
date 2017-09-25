package com.basepage.object;

import com.appium.api.Log;

import org.openqa.selenium.WebElement;

import java.util.List;

import io.appium.java_client.android.AndroidDriver;

import static com.appium.api.ThreadSleepMethod.threadSleep;

/**
 * Created by Administrator on 2017/9/7 0007.
 * homepage 主页操作类入口
 */
public class HomePage {
    AndroidDriver driver;
    private ActionTest ac ;
    private static HomePage homePage;
    private String bannerXpath = "//android.widget.FrameLayout/com.shizhefei.view.viewpager.ViewPager/android.support.v7.widget.RecyclerView/" +
            "android.widget.RelativeLayout[1]/android.support.v4.view.ViewPager/android.widget.ImageView";
    private String bannerID = "org.fungo.fungolive:id/slider";

    public HomePage(){}
    public HomePage(AndroidDriver driver){
        this.driver=driver;
        setDriver();
    }

    //单例模式
    public static HomePage getInstance(AndroidDriver driver){
        if(homePage == null){
            homePage = new HomePage(driver);
        }
        return  homePage;
    }

    /**
     * 新建一个页面操作类
     * 参数：需要操作页面名字
     */
    public void setDriver(){
        ac = new ActionTest(driver,"HomePage");
    }

    /**
     * 搜索功能，在主页时，要先点击一次搜索框跳到搜索页面
     * 参数：搜索的字符
     */
    public  void search(String inputStr){
        threadSleep(5000);
        ac.click("searchButton");
        ac.setValue("inputButton",inputStr);
        threadSleep(2000);
    }

    /**
     * 搜索功能，已经在搜索页的情况
     * 参数：搜索的字符
     */
    public  void searchT(String inputStr){
        threadSleep(5000);
        ac.setValue("inputButton",inputStr);
        threadSleep(2000);
    }

    /**
     * 取消搜索
     * 参数：无
     */
    public  void cancelSearch(){
        threadSleep(2000);
        ac.click("cancel_search");
        threadSleep(2000);
    }

    /**
     * 获取搜索结果列表元素集
     * 参数：无
     */
    public List<WebElement> getSearchResult(String elementName){
        return ac.getElementList(elementName);
    }

    /**
     * 关闭弹窗广告
     * 参数：无
     */
    public void closeAD(){
        threadSleep(2000);
        if(ac.isElementExist("ad_closeButton")){
            Log.info("有广告弹窗，关闭广告...");
            ac.click("ad_closeButton");
        }else {
            Log.info("没有广告弹窗，继续下一步测试...");
        }
    }

    /**
     * 滑动屏幕
     * 参数：direction 方向
     */
    public void swipeTest(String direction){
        ac.swipeScreen(direction);
    }

    /**
     * 滑动元素,banner
     * 参数：direction 方向
     */
    public void swipeBanner(String direction){
        ac.swipElement(bannerXpath,direction);
    }


}
