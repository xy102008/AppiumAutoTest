package com.appium.api;

/**
 * Created by Administrator on 2017/9/5 0005.
 */

public class ThreadSleepMethod {

    public static void threadSleep(int sleepSecond){
        try{
            Thread.sleep(sleepSecond);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
