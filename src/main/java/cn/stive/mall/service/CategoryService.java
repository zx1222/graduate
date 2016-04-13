package cn.stive.mall.service;

import cn.stive.mall.bean.Category;
import cn.stive.mall.dao.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dxt on 16/4/13.
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    public List<Category> getCategory(){
        return categoryDao.getCategory();
    }
}
