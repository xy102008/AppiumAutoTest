package com.basepage.object;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class LivePage {

    public static void main(String[] args){
        InitTestPage it=new InitTestPage("HomePage");
        System.out.println(it.getBy("searchButton"));

    }
}
