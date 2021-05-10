package com.tulun.bean;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author QiangQin
 * * @date 2021/4/26
 */
public class Article {
    private Integer id;
    private Integer categoryId;//分类
    private String title;
    private String content;//把blob转为String了
    private String description;
    private Integer statue;//初始状态（开启或关闭）
    private String author;
    private Date createTime;//创建时间   数据库里的datetime类型
    private Integer showCount;//浏览量、
    private String categoryName;//需要在ArticalMapper里通过id拿name，然后映射到Artilce的java类的属性上
    private String time; //将date类型数据（Sat Apr 24 16:21:17 CST 2021）转化为 好看的String类型
    private String tagName;
    private List<Tag> tagList;//和add_article.jsp上名字保持一致（article.tagList）

    private  List<Tag> tags;//首页展示博客标签要用(HomeController.java 38行)

    private  List<Map<String,String>> tagArray;//添加文章时 暂存 article/add_article.js里 返回的的json形式的tagId
//param会有多个对象（所以List），每一个都可认为是key-value形式(所以Map<String,String>)
// 可以发现只有定义成List<Map<String,String>>   ArticleController里的JsonUtil.fromJson(paramDecode, Article.class);才不会报错且能拿到数值



//注意别用int
    private  Integer tagId;//后台管理，分页展示要用   ArticleMapper.xml里用到了该属性

    public Integer getTagId() {
        return tagId;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", description='" + description + '\'' +
                ", statue=" + statue +
                ", author='" + author + '\'' +
                ", createTime=" + createTime +
                ", showCount=" + showCount +
                ", categoryName='" + categoryName + '\'' +
                ", time='" + time + '\'' +
                ", tagName='" + tagName + '\'' +
                ", tagList=" + tagList +
                ", tags=" + tags +
                ", tagArray=" + tagArray +
                ", tagId=" + tagId +
                '}';
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
    public String getTime() {
//        指定日期要展示的格式(注意大小写)
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        //getTime里自动处理了当前对象的createTime
        return  simpleDateFormat.format(createTime);
    }
    public void setTime(String time) {
        this.time = time;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public List<Map<String, String>> getTagArray() {
        return tagArray;
    }

    public void setTagArray(List<Map<String, String>> tagArray) {
        this.tagArray = tagArray;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatue() {
        return statue;
    }

    public void setStatue(Integer statue) {
        this.statue = statue;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreatTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getShowCount() {
        return showCount;
    }

    public void setShowCount(Integer showCount) {
        this.showCount = showCount;
    }


    public String tagArrayToString( List<Map<String,String>> tagArray) {
        return "" + tagArray; //加“” 变字符串
    }

    public int[] stringToNum(List<Map<String,String>> tagArray){
        String string=tagArrayToString(tagArray);
        //     转义后等价于   [{id=5}, {id=6}]
        int num[]=new int[5],//大小够 数据库标签总个数，或者 单个文章的最多标签数就行
                size=0;//有效数字个数
        //判断是否有 含数字的结构
        while(string.contains( "{id=" ) )
        {
//辅助理解
//            System.out.println("字符串为："+string);
//            System.out.println("{id=起始位置："+string.indexOf("{id="));
//            System.out.println("}起始位置："+string.indexOf("}"));
//            有数字子串（7是转义后的字符串 {"id":" 的长度）
            String subString=(string.substring( string.indexOf("{id=")+4,string.indexOf("}")));
            num[size]=Integer.parseInt(subString);
//            System.out.println("数字："+num[size]);
            size++;
//            输出玩该数字后，删除掉该结构{"id":"5"}  变为 ，{"id":"8"}
//            因为 "}长为2，要删除到  "}的}位置
            string=string.replace(string.substring( string.indexOf("{id="),string.indexOf("}")+1),"");
        }
        num = Arrays.copyOf(num, size);//缩容，只留有效个数字
          return  num;
    }
}
