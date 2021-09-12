package com.tulun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/test1")
    public String test() {
        return "ueditortest";
    }

    //  没有提交到数据库，但又想让两个方法之间传一个参数，所以设置为全局的
    private  String context;

    @RequestMapping("/add")  //http://localhost:8080/test/add
    public String testAdd() {
        return "add_ueditor"; // jsp里触发了add_article_test.js的aveArticle()方法，请求到../test/addContent
    }


    //点击保存按钮，将内容提交到后台
    @RequestMapping("/addContent")
    public String addContent(String description,String content) {
        System.out.println("content:"+content);
        System.out.println("description:"+description);
        context = content;
        return "redirect:/test/detail"; //  请求 详情页面
    }

    //详情页面
    @RequestMapping("/detail")
    public String detail(Model model){
        model.addAttribute("content", context);
        return "ueditor_detail";
    }
}

