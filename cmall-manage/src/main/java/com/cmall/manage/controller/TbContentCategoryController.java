package com.cmall.manage.controller;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmall.manage.service.TbContentCategoryService;
import com.cmall.commons.pojo.EasyUiTree;
import com.cmall.commons.pojo.CmallResult;
import com.cmall.pojo.TbContentCategory;

@Controller
public class TbContentCategoryController {
    @Resource
    private TbContentCategoryService tbContentCategoryServiceImpl;
    
    /*
     * 查询商品类目
     */
    @RequestMapping("content/category/list")
    @ResponseBody
    public List<EasyUiTree> showCategory(@RequestParam(defaultValue="0") long id){
	return tbContentCategoryServiceImpl.showCategory(id);
    }
    
    /*
     * 新增内容类目
     */
    @RequestMapping("content/category/create")
    @ResponseBody
    public CmallResult create(TbContentCategory cate){
	return tbContentCategoryServiceImpl.create(cate);
    }
    
    /*
     * 重命名
     */
    @RequestMapping("content/category/update")
    @ResponseBody
    public CmallResult update(TbContentCategory cate){
	return tbContentCategoryServiceImpl.update(cate);
    }
    
    /*
     * 删除
     */
    @RequestMapping("content/category/delete")
    @ResponseBody
    public CmallResult delete(TbContentCategory cate){
	return tbContentCategoryServiceImpl.delete(cate);
    }
}
