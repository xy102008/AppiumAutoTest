package com.appium.api;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jiangwei on 2017/9/5 0005.
 * 说明：提供截图功能
 */

public class ScreenshotMethodObject {
    // public AndroidDriver driver;
    public Date data;

    /**
     * 获取当前测试时间
     * 参数：无
     * return  返回当前日期 指定格式
     **/
    public  String getTime(){
        SimpleDateFormat data = new SimpleDateFormat("yyyyMMddhhmm");
        return data.format(new Date());
    }

    /**
     * 截图（路径写死）
     * 参数：截图的图片的名字
     * return  无
     **/
    public void Screenshot(TakesScreenshot drivername){
        String filename=getTime() + ".jpg";
        File scrFile = drivername.getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(scrFile,new File("d://ftp"+"\\"+filename));
        } catch (IOException e) {
            Log.error("保存失败");
            e.printStackTrace();
        }
        finally {
            Log.info("Screen shot finished, path in "
                   +"d://ftp");
        }
    }

    /**
     * 截图（可自定义路径）
     * 参数：截图的图片的名字，和pathName 文件路径
     * return  无
     **/
    public void Screenshot(TakesScreenshot drivername,String pathName){
        String filename=getTime() + ".jpg";
        File scrFile = drivername.getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(scrFile,new File(pathName+"\\"+filename));
        } catch (IOException e) {
            Log.error("保存失败....");
            e.printStackTrace();
        }
        finally {
            Log.info("Screen shot finished, path in "
                    + pathName);
        }
    }

}
