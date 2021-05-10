package com.tulun.dao;

import com.tulun.bean.Article;
import com.tulun.bean.Category;
import com.tulun.bean.Tag;
import org.apache.ibatis.annotations.Param;
//数据持久层
//model就是数据 dao和bean
import java.util.HashMap;
import java.util.List;

/**
 * @author QiangQin
 * * @date 2021/4/26
 */
public interface ArticleMapper {
    public int addArticle( Article article);//返回是影响的条数   想返回自增的id值需要sql标签加个属性
    public Article getArticleById(Integer id);

    public List<Article> getAllArticleByCategoryId(Integer categoryId);
    public List<Article> getAllArticle();

    public void increShowCount(Integer id);

//    要写到ArticleTagMapper里吗？ 若写，ArticleService怎么保证事务？？？
//    public int setArticleTag(@Param(value = "articleId") Integer articleId, @Param(value = "tagId") Integer tagId);


    //查询符合要求的数据总个数   注意param类型
    public Integer selectCountByArticle(HashMap<String, Object> param);
    //分页查询符合要求的数据
    public List<Article> selectArticleByPager(HashMap<String, Object> param);

    public int updateStatue(Article article);


}
