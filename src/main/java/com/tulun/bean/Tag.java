package com.tulun.bean;

/**
 * @author QiangQin
 * * @date 2021/4/26
 */
public class Tag {
    private  Integer Id;
    private  String tagName;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String  tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "Id=" + Id +
                ", tagName=" + tagName +
                '}';
    }
}
