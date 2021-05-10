package com.tulun.service;

import com.tulun.bean.Article;
import com.tulun.bean.Category;
import com.tulun.bean.Tag;
import com.tulun.dao.ArticleTagMapper;
import com.tulun.dao.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author QiangQin
 * * @date 2021/4/26
 */
@Service
public class ArticleTagService {
    @Autowired
    private ArticleTagMapper articleTagMapper;

//    已知：标签至少选一个  且  必须是标签库的
    public void  setArticleTag(int[] num,int articleId) {
        for (int tagId:num) //每次迭代从num数组里取一个数，暂存到tagId里备用
         {
//            System.out.println(tagId);
//            一个文章可以多个标签(注意和接口方法的属性位置对应)
            articleTagMapper.setArticleTag(articleId,tagId);
        }
    }

}
