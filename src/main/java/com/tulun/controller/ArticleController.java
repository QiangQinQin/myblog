package com.tulun.controller;
//（表现层）
//spring管理 将 service层实例 注入到 controller层
import com.tulun.bean.*;
import com.tulun.config.Config;
import com.tulun.service.ArticleService;
import com.tulun.service.ArticleTagService;
import com.tulun.service.CategoryService;
import com.tulun.service.TagService;
import com.tulun.util.JsonUtil;
import com.tulun.util.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author QiangQin
 * * @date 2021/4/26
 */
@Controller
//分类管理    jsp/article文件夹里jsp所对应的控制器
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private TagService tagService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleTagService articleTagService;
//   新建 博客
    @RequestMapping("/add") //http://localhost:8080/article/add
    public String add(Model model, HttpSession session) {
        // 只有登录情况下才能完成新增功能
        User user = (User) session.getAttribute(Config.LoginKey);
        if(user==null){
            return  "redirect:../login";//因为当前Url在Artical里
            // http://localhost:8080/article/add   到   http://localhost:8080/login，在UserController里处理URL
        }

        //从数据库 获取类别数据 供下拉菜单选择
        List<Category> categories = categoryService.selectAllCategory();
        //获取标签数据
        List <Tag> tags = tagService.selectAllTag();
//        注意和jsp/article/add_article.jsp里的类别名字（categoryList）一样
        model.addAttribute("categoryList", categories);
        model.addAttribute("tagList", tags);
        return "article/add_article"; //在jsp下的子目录artilce里
    }

//    提交 博客 内容（需要 先 经过上面http://localhost:8080/article/add提供 下拉菜单信息）
    @RequestMapping("/addContent")
    @ResponseBody  //表示add_aticle.jsp点击保存按钮后，触发add_article.js,js里响应回来 的是 包含3部分的json数据
    public Result  addContent(String param,String content,String description, HttpSession session) throws UnsupportedEncodingException {
//        测试解码前后
//        System.out.println("param"+param);
//        System.out.println("content"+content);
//        System.out.println("description"+description);
        // 假设已经登录
        User user = (User) session.getAttribute(Config.LoginKey);//根据登录用户设置文章作者
        String paramDecode = URLDecoder.decode(param, "utf-8");
//        System.out.println("观察tagList"+paramDecode);
        String contentDecode = URLDecoder.decode(content, "utf-8");
        String descriptionDecode = URLDecoder.decode(description, "utf-8");
        //从字符串param解析为Article对象（免的new一个Article，再置 param里有的标题  类别  标签列表值），
        // id不用指定，是自增的
//     此处的Article是给页面展示用的，是数据库里好几个表属性的联合
        Article articleDecode = JsonUtil.fromJson(paramDecode, Article.class);
        //将js/article/add_article.js提交的数据解析，得到"tagArray":[{"id":"5"},{"id":"6"}]
        //从字符串中分离出数字串，存到t_article_tag里，
        // 然后展示页面详情时通过articleId在t_article_tag里找tagId,再找tagName，存到tagList里，供article_detail.jsp展示用

   //输入tagArray字符串 ，提取成数字数组，逐条插入t_article_tag
  // 好像不用存到Article.tagList里,数据库里有就行了，article_detail.jsp显示需要时再生成吧
        int[] num=articleDecode.stringToNum(articleDecode.getTagArray());
//      有id了不用从json中拿，从数据库中拿标签名？？？？？？？？？？？？？
//        让老师看源码，找标签 和 事务的错误？？？？？？？？？？？？？？？？
        articleDecode.setContent(contentDecode);
        articleDecode.setDescription(descriptionDecode);
        articleDecode.setStatue(0);//默认值 表示正常
        articleDecode.setCreatTime(new Date());
//      articleDecode.setAuthor("QiangQin");
// 可通过HttpSession拿到用户登录信息
        articleDecode.setAuthor(user.getName());
        articleDecode.setShowCount(0);
//
////       插入数据库
       Article article=articleService.addArticle(articleDecode);
//        System.out.println(article);
        articleTagService.setArticleTag(num,article.getId());
//        System.out.println(article);//看有没有tagList和id
//        // 设置tag表
//        // (Column 'articleId' cannot be null)  即设置的属性还不够，要返回Article才有id,但是interface里不能返回Article
//        articleTagService.setArticleTag(article);
////        设置TagList属性
//        List<Integer> tagList= articleTagService.getTagListName(article.getId());
//        articleDecode.setTagList(tagList);
        if(article!=null){
            return  new Result("success","处理成功");
        }else{
            return  new Result("fail","插入数据库失败");
        }
//        System.out.println("paramDecode"+paramDecode);
//        System.out.println("contentDecode"+contentDecode);
//        System.out.println("descriptionDecode"+descriptionDecode);
//        假装处理成功
//        return  new Result("success","处理成功");
    }


//   获取 指定id的文章  详情
// http://localhost:8080/article/1
// home/articlepager.jsp里18行，文章名也可以跳转
    @RequestMapping("/{id}")//{id}表示可变，通配符  下面将其和形参id绑定
    public String detail(@PathVariable("id") Integer id, Model model,String param) throws UnsupportedEncodingException {

        //已经有了categoryName和tagList属性值
        Article article = articleService.getArticleById(id);
        if(article==null){//数据有效  就是能获取到  article对象
            return "404";
        }
        // （自己）ArticleTagMapper里面的getTagByArticalId可以获取标签？？？？？？？？？？？？？
        // 访问量++，刷新页面 数字的变化才会展现
        articleService.increShowCount(id);
        model.addAttribute("article", article);
        return "article/article_detail";
    }

    //管理员后台展示的主页面
    @RequestMapping("/back")//http://localhost:8080/article/back
    public String back(Model model) {
        //类别列表
        List <Category> categories = categoryService.selectAllCategory();
        //标签列表
        List <Tag> tags = tagService.selectAllTag();
        //带信息回去
        model.addAttribute("categoryList", categories);
        model.addAttribute("tagList",tags);
        return "article/article_list";//前后缀在spring-mvc.xml里已指定
// 即返回数据给 后台展示页面article/article_list.jsp,
// 然后调用js/article/article.js，
// js会自动执行$(function()，该方法会调用loadArticleList()，从而请求load的URL，进行分页的内容加载
    }

    //后台的 分页 页面(或者模糊搜索也请求到load)
    @RequestMapping("/load")
//    load参数名和js/article/article.js里ajax提供的保持一样
    public String load(Model model, Pager<Article> pager, String param) throws UnsupportedEncodingException {
//  不用解密  param里有标题 类别和标签
//   System.out.println("参数：param："+param+"，pager："+pager);
        //转化为对象
        Article article = JsonUtil.fromJson(param, Article.class);
//        && "".equals(article.getTitle())  好像没啥用！！！！
//        if (article != null && article.getTitle() != null ) {
//            //标题参数不为空(即模糊搜索)时，（因为article.js传过来时加密了）需要进行 解码 处理
//            String title = URLDecoder.decode(article.getTitle(), "utf-8");
//            article.setTitle(title);
//        }

        //分页相关的
        //需要获取  当前条件下数据的个数articleCount（有可能是模糊查询，结果数不多）
//        if (pager.getPage() == 1) {
        //当前条件下第一次查询
        Integer articleCount = articleService.getArticleCount(article);
//        System.out.println("符合条件："+articleCount);
        pager.setTotalCount(articleCount);
//        }

        List <Article> articles = articleService.getArticlesByPage(article, pager);
        model.addAttribute("articleList",articles);//article_pager.jsp里用到了articleList属性

        return "article/article_pager";
    }

//article_pager.jsp调用article.js里的updateStatue方法，请求到下面这个URL,然后给js方法返回一个Result数据，以data的形参接受
    @RequestMapping("/updateStatue")
    @ResponseBody
    public Result updateStatue(@Param("id") Integer id, @Param("statue") Integer statue) {

        Article article = new Article();
        article.setId(id);
        article.setStatue(statue);//注意命名Statue  和Category

        if (articleService.updateStatue(article)) {
            return new Result("success","成功");
        } else {
            return new Result("fail","修改失败");
        }
    }
}
