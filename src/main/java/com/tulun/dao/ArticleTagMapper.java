package com.tulun.dao;

import com.tulun.bean.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author QiangQin
 * * @date 2021/4/26
 */
public interface ArticleTagMapper {
//   （自己） 要和ArticleMapper写到一起吗   多参数传递，将形参绑到@Param上
    public void setArticleTag(@Param(value = "articleId") Integer articleId, @Param(value = "tagId") Integer tagId);



// (老师) 获取博客id获取所有其标签信息，在ArticleService里面用了
    public  List<Tag> getTagByArticalId(Integer articleId);
}
