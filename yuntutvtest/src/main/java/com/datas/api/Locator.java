package com.datas.api;

/**
 * Created by Jiangwei on 2017/9/11 0011.
 */

public class Locator {

    private String buttonValue;  //定位地址
    private ByType byType;  //定位方式

    /**
     * 定位类型枚举
     **/
    public enum ByType{
        by, xpath, linkText, id, name, className
    }


    public Locator() {}

    /**
     * Locator构造器，默认定位类型By.id
     *
     * @param element
     */
    public Locator(String buttonValue) {
        this.buttonValue = buttonValue;
        this.byType = ByType.id;
    }



    public Locator(String buttonValue, ByType byType) {
        this.buttonValue = buttonValue;
        this.byType = byType;
    }

    public String getButtonValue(){
        return buttonValue;
    }

//    public ByType getBy() {
//        return byType;
//    }

    public void setBy(ByType byType) {
        this.byType = byType;
    }

    public ByType getByType() {
        return byType;
    }


}