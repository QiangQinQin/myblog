package com.tulun.dao;

import com.tulun.bean.Category;
import com.tulun.bean.Tag;

import java.util.List;

/**
 * @author QiangQin
 * * @date 2021/4/26
 */
public interface CategoryMapper {
    public List<Category> getAllCategory();


//   ArticalMapper.xml里不通过联合查询得到CategoryName时  用此
    public  Category getCategoryById(Integer id);
}
