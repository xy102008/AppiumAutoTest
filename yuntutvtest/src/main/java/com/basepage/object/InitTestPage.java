package com.basepage.object;

import com.appium.api.Log;
import com.datas.api.LocaterPaser;
import com.datas.api.Locator;

import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.HashMap;

import io.appium.java_client.android.AndroidDriver;

/**
 * Created by Jiangwei on 2017/9/11 0011.
 * 初始化页面元素
 */

public class InitTestPage {
    public AndroidDriver driver;
    public String pageName;
    LocaterPaser locaterPaser=new LocaterPaser();
    HashMap<String, Locator> locatorMap;
    By by=null;

    public InitTestPage(){}

    /**
     * 初始化一个页面对象，创建一个LocaterPaser，和 HashMap
     * 参数： 页面名称
     * return 无
     **/
    public InitTestPage(String pageName){
        locatorMap=locaterPaser.getLocator(pageName);
        this.pageName=pageName;
    }

    /**
     * 提供多个构造函数供选择
     * 参数： 页面名称和drive对象
     * return 无
     **/
    public InitTestPage(AndroidDriver driver,String pageName){
        locatorMap=locaterPaser.getLocator(pageName);
        this.driver=driver;
        this.pageName=pageName;
    }

    /**
     * 获取button信息
     * 参数：itemName  需要读取那个button的信息，
     * 返回 By 类型
     **/
    public By getBy(String buttonName){
        ArrayList valueList=getXmlDatas(buttonName);
        Locator.ByType idType = (Locator.ByType) valueList.get(0);
        String value = (String)valueList.get(1);
        switch (idType) {
            case xpath:
                by = By.xpath(value);
                break;
            case id:
                by = By.id(value);
                break;
            case name:
                by = By.name(value);
                break;
            case className:
                by = By.className(value);
                break;
            default:
                by = By.id(value);
        }
        return by;
    }

    /**
     * 读取指定page的数据
     * locatorMap是存贮了page页面元素数据的Map，String的值为key（这里取button名字），Locator对象存放这个key的value和type
     * 参数：itemName  需要读取那个button的信息，
     * 找到返回 ArrayList类型，存贮了idType、value信息
     **/
    public ArrayList getXmlDatas(String buttonName){
        ArrayList valueList=new ArrayList();
        Locator.ByType idType = null;
        String value = null;
        for(String key:locatorMap.keySet()){
            Log.info("开始在HashMap查找页面元素...");
            Log.info("key= "+key+" value= "+locatorMap.get(key).getButtonValue());
            if(key.equals(buttonName)){
                idType = locatorMap.get(key).getByType();
                value = locatorMap.get(key).getButtonValue();
                valueList.add(idType);
                valueList.add(value );
                Log.info("找到所需要的key，并返回该key的button类型和value...");
                return valueList;
            }
        }

        return valueList;


    }


}
