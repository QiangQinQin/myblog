package com.tulun.service;

import com.tulun.bean.Category;
import com.tulun.dao.ArticleMapper;
import com.tulun.dao.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author QiangQin
 * * @date 2021/4/26
 */
@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    public List<Category> selectAllCategory(){
        return  categoryMapper.getAllCategory();
    }


    public  Category getCategoryById(Integer id){
        return  categoryMapper.getCategoryById(id);
    }

}
