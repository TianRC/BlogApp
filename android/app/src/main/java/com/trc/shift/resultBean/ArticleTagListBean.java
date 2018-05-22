package com.trc.shift.resultBean;

import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by trc on 2018/5/4.
 */

public class ArticleTagListBean {


    /**
     * tags : [{"name":"Android","tagId":"1"},{"name":"React","tagId":"2"},{"name":"R N","tagId":"3"}]
     * token : 199203195113
     */

    private String token;
    private List<TagsBean> tags;

    public static ArticleTagListBean objectFromData(String str) {

        return new Gson().fromJson(str, ArticleTagListBean.class);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public static class TagsBean {
        /**
         * name : Android
         * tagId : 1
         */

        private String name;
        private String tagId;

        public static TagsBean objectFromData(String str) {
            return new Gson().fromJson(str, TagsBean.class);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTagId() {
            return tagId;
        }

        public void setTagId(String tagId) {
            this.tagId = tagId;
        }
    }
}
