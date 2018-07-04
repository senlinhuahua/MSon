package com.xsl.mson.entity;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/9/2 0002.
 */

public class Guniang implements Comparable<Guniang>, Serializable {
    private String _id;
    private Date createdAt;
    private String desc;
    private Date publishedAt;
    private String source;
    private String type;
    private String url;
    private List<String> images;
    private boolean used;
    private String who;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        if (TextUtils.isEmpty(who)) {
            return "xxx";
        }
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Guniang{" +
                "_id=" + _id +
                ", createdAt='" + createdAt + '\'' +
                ", desc='" + desc + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", images='" + images + '\'' +
                ", used=" + used +
                ", who='" + who + '\'' +
                '}';
    }

    @Override
    public int compareTo(@NonNull Guniang o) {
        return o.getPublishedAt().compareTo(this.getPublishedAt());
    }
}
