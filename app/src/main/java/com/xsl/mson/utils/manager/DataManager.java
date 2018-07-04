package com.xsl.mson.utils.manager;

import com.xsl.mson.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by forest on 2017/9/7 0007.
 */

public class DataManager {

    public static List<User> getNativeUserData(){
        List<User> data = new ArrayList<>();
        for (int i = 0;i<50;i++){
            data.add(new User("201"+i,"data"+i));
        }

        return data;
    }

    public static String [] bannerUri = {"https://ws1.sinaimg.cn/large/610dc034ly1fir1jbpod5j20ip0newh3.jpg",
    "https://ws1.sinaimg.cn/large/610dc034ly1fik2q1k3noj20u00u07wh.jpg","https://ws1.sinaimg.cn/large/610dc034ly1fiednrydq8j20u011itfz.jpg"};
}
