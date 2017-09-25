package com.datas.api;

/**
 * Created by Jiangwe on 2017/9/11 0011.
 */

import com.appium.api.Log;
import com.datas.api.Locator.ByType;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

public class LocaterPaser {
    private Document doc;
    private String resourceName;

    public LocaterPaser(){
        File in=getInputFileAsStream();//得到文档
        SAXReader reader=new SAXReader(); //创建解析器
        try{
            doc = (Document) reader.read(in); //解析文档
            Log.info("解析xml文件成功...");
        }catch(DocumentException e){
            Log.error("解析xml文件出错...");
            e.printStackTrace();
        }
    }
    public  File getInputFileAsStream() {
        // TODO Auto-generated method stub
        String packageName = "";
        String xmlPath=System.getProperty("user.dir") + "\\src\\main\\conf\\LocaterPaser.xml";
        Log.info("页面对象路径在："+xmlPath);
        File file = new File(xmlPath);
        if (!file.exists()) {
            Log.error("Can't find " + xmlPath);
        }
        Log.info("Searching for default input file: "+xmlPath);
        return file;
    }

    /**
     * 把page的页面元素信息存在HashMap
     * HashMap<String, Locator>，String的值为key（这里取button名字），Locator对象存放这个key的value和type
     * 参数： pageName  需要存贮的页面名称 ，
     * 找到返回 HashMap类型
     **/
    public HashMap<String, Locator> getLocator(String pageName){
        HashMap<String, Locator> locatorMap = new HashMap<String, Locator>();
        locatorMap.clear();
        Locator loca = null;
        Element datasets=(Element) this.doc.getRootElement();        //获取根节点元素对象
        //第一次迭代，获取所有page页面
        for(Iterator d=datasets.elementIterator("page"); d.hasNext();){
            Element page = (Element) d.next();
            if(page.attributeValue("pageName").equals(pageName)){             //找到自己需要的page
                Log.info("你要找的pageName is: "+ page.attributeValue("pageName"));
                Log.info("开始迭代对象库的元素列表....");
                //第二次迭代locator，即具体页面的元素列表
                for(Iterator<?> data = page.elementIterator("locator"); data.hasNext();){
                    String type = null;
                    String value = null;
                    String locatorName = null;
                    Element locator = (Element) data.next();
                    //利用locator的name属性值找到想要的locator
                    Log.info("开始在对象库迭代控件值列表....");
                    value=locator.attributeValue("value");
                    type = locator.attributeValue("type");
                    locatorName = locator.attributeValue("name"); //这个是locator的描述名字
                    Log.info("控件的描述是：" + locatorName);
                    loca = new Locator(value,getByType(type));
                    locatorMap.put(locatorName, loca);
                    Log.info("----------");

                }
                Log.info("迭代对象库完成...");
                return locatorMap;
            }
        }

        return locatorMap;
    }


    public static ByType getByType(String type) {
        ByType byType = ByType.xpath;
        if (type == null || type.equalsIgnoreCase("xpath")) {
            byType = ByType.xpath;
        } else if (type.equalsIgnoreCase("id")) {
            byType = ByType.id;
        } else if (type.equalsIgnoreCase("name")) {
            byType = ByType.name;
        } else if (type.equalsIgnoreCase("className")) {
            byType = ByType.className;
        }
        return byType;
    }
}
