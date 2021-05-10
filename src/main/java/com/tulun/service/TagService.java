package com.tulun.service;

import com.tulun.bean.Tag;
import com.tulun.dao.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author QiangQin
 * * @date 2021/4/26
 */
@Service
public class TagService {
    @Autowired
    private TagMapper tagMapper;//注入Dao层
    public List<Tag> selectAllTag(){
        return  tagMapper.getAllTag();
    }

}
