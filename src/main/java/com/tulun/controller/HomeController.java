package com.tulun.controller;

import com.tulun.bean.Article;
import com.tulun.bean.Category;
import com.tulun.bean.Tag;
import com.tulun.service.ArticleService;
import com.tulun.service.CategoryService;
import com.tulun.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author QiangQin
 * * @date 2021/4/29
 */
@Controller
public class HomeController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;
    @Autowired
    private ArticleService articleService;

//    浏览器输入IP＋端口，就能跳转到首页页面
////跳转首页页面( 父 页面，只有标签和类别)
    @RequestMapping( "/")
    public String index (Model model) {
        List<Category> categories = categoryService.selectAllCategory();
        //理论上应该展示热点标签，而不是所有标签？？？？？？？？？？？？？？？？
        List<Tag> tags = tagService.selectAllTag();
        model.addAttribute("categoryList", categories);
        model.addAttribute("tagList", tags);
        return "home/index";
    }

//    加载首页内容（加载文章 子 页面）
//   上面提交到home/index.jsp以后  通过js到了article/article_list.js(里面id为99999999999时才分页显示,即没分页！！！),
// 然后js里的ajax又跳转到HomeController层的lodPageAll，带上articleList参数返回到articlepager.jsp
    @RequestMapping("loadPageAll")
    public String loadAll(Model model){
        List<Article> allArticle=articleService.getAllArticle();//处理了时间和标签
        model.addAttribute("articleList",allArticle);//因为articlepager.jsp里要用到articleList
        return "home/articlepager";
    }



    ////按照类别进行加载(相当于父页面：标签 和 类别)
    @RequestMapping("/loadPage/{id}")
    //和home/index.jsp里对应    @PathVariable参数绑定
    public String loadPage(Model model, @PathVariable("id") Integer id) {
        //通过类别id来加载类别信息
        Category category = categoryService.getCategoryById(id);

        //当前类别信息（“ca”和articlepager1里的名字保持一致）
        model.addAttribute("ca", category);

        List <Category> categories = categoryService.selectAllCategory();
        List <Tag> tags = tagService.selectAllTag();

        model.addAttribute("categoryList", categories);
        model.addAttribute("tagList",tags);

        return "home/articlepager1";//jsp
    }

    //加载 指定类别的 内容（子页面：文章内容）
    // home/articlepager1.jsp提交以后，js/category/article_list过来的
    @RequestMapping("/loadPage1/{id}")
    public String loadPage1(Model model, @PathVariable("id") Integer id) {
        List <Article> articles = articleService.getAllArticleByCategoryId(id);
        model.addAttribute("articleList", articles);
        return "home/articlepager";
    }

}
