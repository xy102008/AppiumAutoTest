package com.testcase;

import com.appium.api.Log;
import com.basepage.object.HomePage;
import com.basepage.object.PlayerPage;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import io.appium.java_client.android.AndroidDriver;

import static com.appium.api.ThreadSleepMethod.threadSleep;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Administrator on 2017/9/18 0018.
 */

public class SearchTest {
    AndroidDriver<?>  driver ;
    HomePage homePage;
    PlayerPage ply;

    @BeforeClass
    public void beforeClass(){
        this.driver = Utils.driver;
        homePage = HomePage.getInstance(driver);
        ply = PlayerPage.getInstance(driver);
    }

    @Test(description = "搜索用例一")
    public void searchTestA()  {
        threadSleep(5000);
        Log.info("搜索测试开始...");
        homePage.search("吴京");
        threadSleep(2000);
        List<WebElement> list = homePage.getSearchResult("search_title_name");
        assertThat(list.get(0).getText()).contains("吴京");
       // homePage.cancelSearch();
    }

    @Test(description = "搜索用例二")
    public void searchTestB()  {
        threadSleep(5000);
        Log.info("搜索测试开始...");
        homePage.searchT("成龙");
        threadSleep(2000);
        List<WebElement> list = homePage.getSearchResult("search_title_name");
        assertThat(list.get(0).getText()).contains("成龙");
      //  homePage.cancelSearch();
    }

    @Test(description = "非法搜索内容用例")
    public void searchTestC()  {
        threadSleep(5000);
        Log.info("搜索测试开始...");
        homePage.searchT("非法");
        threadSleep(2000);
        List<WebElement> list = homePage.getSearchResult("no_search_result");
        assertThat(list.get(0).getText()).contains("没有搜到内容");
     //   homePage.cancelSearch();
    }

    @Test(description = "热门搜索用例")
    public void searchTestD()  {
        Boolean flag = false;
        threadSleep(5000);
        Log.info("热门搜索测试开始...");
        homePage.searchT("");
        List<WebElement> list = homePage.getSearchResult("hot_search");
        for(int i=0; i<list.size(); i++){
            if(list.get(i).getText().contains("那年花开")){
                flag = true;
            }
        }
        Assertions.assertThat(flag);
    }

    @Test(description = "搜索历史测试")
    public void searchHistory()  {
        threadSleep(5000);
        Log.info("搜索历史测试开始...");
        homePage.searchT("洪金宝");
        threadSleep(2000);
        List<WebElement> list = homePage.getSearchResult("search_title_name");
        list.get(0).click();
        if(ply.getCurrentActivity().equals(ply.playMainActivity)){
            Log.info("跳到播放页成功...播放15s");
            threadSleep(15000);
            ply.goBack();
            Log.info("返回搜索页");
        }else {
            Log.info("跳到播放页失败");
        }
        homePage.cancelSearch();
        homePage.search("");
        List<WebElement> ls = homePage.getSearchResult("hot_search");
        assertThat(ls.get(0).getText()).contains("洪金宝");
        //  homePage.cancelSearch();
    }

    @AfterClass
    public void exitSearch(){
        homePage.cancelSearch();
    }

}
