package com.basepage.object;

import com.appium.api.ElementMethodObject;

import io.appium.java_client.android.AndroidDriver;

/**
 * Created by Jiangwei on 2017/9/7 0007.
 * 用于初始化页面元素
 */

public class MyPage {

    private AndroidDriver driver;

    public static MyPage myPage;
    private ActionTest ac ;

    ElementMethodObject emb=new ElementMethodObject();

    public MyPage(AndroidDriver drive){
        this.driver = drive;
        getMypage();
    }

    //单例模式
    public static MyPage getInstance(AndroidDriver driver){
        if(myPage == null){
            myPage = new MyPage(driver);
        }
        return  myPage;
    }

    /**
     * 新建一个页面操作类
     * 参数：需要操作页面名字
     */
    public void getMypage(){
        ac = new ActionTest(driver,"minePage");
    }


    /**
     * 获取登陆状态
     */
    public Boolean getLoginStat(){
        Boolean loginStat = true;
        if(ac.isElementExist("logintStat")){
            loginStat = false;
        }
        return loginStat;
    }


    /**
     * 获取VIP状态
     */
    public Boolean getVipStat(){
        Boolean vipStat = false;
        if(ac.getValue("logintStat").equals("未登录")){
            vipStat = false;
        }
        return vipStat;
    }



}
