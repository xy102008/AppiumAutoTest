package com.testcase;

import com.appium.api.Log;
import com.basepage.object.HomePage;
import com.basepage.object.PlayerPage;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import io.appium.java_client.android.AndroidDriver;

import static com.appium.api.ThreadSleepMethod.threadSleep;

/**
 * Created by jiangwie on 2017/9/20 0020.
 */

public class PlayTest {

    AndroidDriver<?> driver ;
    PlayerPage playerPage;
    HomePage homePage;

    @BeforeClass
    public void beforeClass(){
        this.driver = Utils.driver;
        playerPage = PlayerPage.getInstance(driver);
        homePage = HomePage.getInstance(driver);

    }

    @Test(description = "搜索并播放用例")
    public void playTest(){
        Log.info("播放测试开始...");
        homePage.search("李连杰");
        List<WebElement> list = homePage.getSearchResult("search_title_name");
        list.get(0).click();
        threadSleep(5000);
        playerPage.goBack();
        threadSleep(3000);
    }
}
