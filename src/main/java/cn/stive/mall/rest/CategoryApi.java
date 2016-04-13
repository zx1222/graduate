package cn.stive.mall.rest;

import cn.stive.mall.bean.Category;
import cn.stive.mall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by dxt on 16/4/13.
 */
@Controller
public class CategoryApi extends BaseHandler{

    @Autowired
    CategoryService categoryService;

    @RequestMapping("admin/category/list")
    @ResponseBody
    public Response getCategory(){
        return this.success(categoryService.getCategory());
    }
}
