package com.tulun.bean;

/**
 * @author QiangQin
 * * @date 2021/4/26
 */

//博客类别（现在只添加要用到的属性）
public class Category {
    private Integer id;
    private  String categoryName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
