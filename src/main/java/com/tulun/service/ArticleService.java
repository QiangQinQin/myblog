package com.tulun.service;
//业务层
// spring 来管理service类，
// 并将dao层的对象实例注入到service中使用。
// 另外事务相关的操作也通过spring进行管理
import com.tulun.bean.Article;
import com.tulun.bean.Article;
import com.tulun.bean.Category;
import com.tulun.bean.Tag;
import com.tulun.dao.ArticleMapper;
import com.tulun.dao.ArticleTagMapper;
import com.tulun.dao.CategoryMapper;
import com.tulun.util.Pager;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

/**
 * @author QiangQin
 * * @date 2021/4/26
 */


@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleTagMapper articleTagMapper;

// ArticalMapper.xml里不通过联合查询得到CategoryName时用此
    @Autowired
    private CategoryMapper categoryMapper;

    // 事务（都完成  或者  都不完成）
    //添加文章的多个标签怎么处理？？？？？？？？？？？？？？
    public Article addArticle(Article article) {
        //将数据插入到 t_Aticle 数据库，并返回是article对象
        // 返回的article 带了主键id；只能有一个分类；但可能有多个标签名List<Tag>
//  has an unsupported return type:  不能返回Article
//      将tagId和articleId对应关系插入到t_article_tag表中（多对多）
        articleMapper.addArticle(article);
//        System.out.println(article);//有id了
        return article;
    }

    public Article getArticleById(int id) {
        //联合查询，也找到了catrgoryName属性到Article对象
        // 在article/article_detail.jsp里显示时处理了转化了time
        Article article= articleMapper.getArticleById(id);
        List<Tag> tagList = articleTagMapper.getTagByArticalId(article.getId());
        article.setTagList(tagList);//article_detail.jsp展示文章详情时需要article.tagList属性
        return  article;
    }


    //    获取所有类别的博客信息
    public List<Article> getAllArticle() {
        List<Article> allArticle = articleMapper.getAllArticle();
        return dataAndTagHandler(allArticle);
    }

    //获取某一类的所有博客
    public List<Article> getAllArticleByCategoryId(Integer categoryId) {
        List<Article> articles = articleMapper.getAllArticleByCategoryId(categoryId);
        return dataAndTagHandler(articles);
    }

    //    封装 获取所有博客的统一业务逻辑
    private List<Article> dataAndTagHandler(List<Article> articles) {
        if (articles != null && articles.size() > 0) {
            //要处理  时间格式 每个文章的标签
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月DD日HH时MM分SS秒");
//           dateFormat.format(a.getCreateTime())
            for (Article a : articles) {
                //处理createTime的格式，但是articlepager.jsp获取的是article.time属性
                a.setTime(a.getTime());//getTime里自动处理了当前对象的createTime
                //获取文章标签
                List<Tag> tagList = articleTagMapper.getTagByArticalId(a.getId());
                a.setTags(tagList);

                // ArticalMapper.xml里不通过联合查询得到CategoryName时用此！！！！！！
                Category category = categoryMapper.getCategoryById(a.getCategoryId() ) ;
//                System.out.println("Article"+a+"》》》》》》category"+category);
                if (category != null) {
              //      安全性检查完设置Article类的CategoryName属性
                    a.setCategoryName(category.getCategoryName());
                }
            }
        }
        return articles;
    }


     public  void increShowCount(Integer id){
        articleMapper.increShowCount(id);
     }


//后台管理页面 模糊查询
    //获取符合条件的数据总数量
    public Integer getArticleCount(Article article) {
        HashMap<String, Object> hashMap = new HashMap <>();
        hashMap.put("article", article); //因为ArticleMapper.xml里的where用到了article对象的属性
        Integer integer = articleMapper.selectCountByArticle(hashMap);
//        没用到pager
        return integer;
    }

    //对符合条件的数据进行 分页处理
    public List<Article> getArticlesByPage(Article article,Pager pager) {
        HashMap <String, Object> hashMap = new HashMap <>();
//        因value为不同类型，所以HashMap<String, Object>
        hashMap.put("article", article);
  //        Page就提供start和limit
        hashMap.put("start",pager.getStart());
        hashMap.put("limit", pager.getLimit());

        List <Article> articles = articleMapper.selectArticleByPager(hashMap);
        return articles;
    }


    //修改状态
    public boolean updateStatue(Article a) {
        int i = articleMapper.updateStatue(a);
        if (i> 0) {
            return true;
        } else {
            return false;
        }
    }
}