package com.xsl.mson.entity;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/8/29 0029.
 */

public class User {

    private String time;
    private String name;

    @Inject
    public User() {
    }

    public User(String time, String name) {
        this.time = time;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }
}
