package com.cmall.manage.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmall.manage.service.TbContentService;
import com.cmall.commons.pojo.EasyUIDataGrid;
import com.cmall.commons.pojo.CmallResult;
import com.cmall.pojo.TbContent;

@Controller
public class TbContentController {
    @Resource
    private TbContentService tbContentServiceImpl;
    
    /*
     * 显示内容信息
     */
    @RequestMapping("content/query/list")
    @ResponseBody
    public EasyUIDataGrid showContent(long categoryId,int page,int rows){
	return tbContentServiceImpl.showContent(categoryId, page, rows);
    }
    
    @RequestMapping("content/save")
    @ResponseBody
    public CmallResult save(TbContent content){
	CmallResult er=new CmallResult();
	int index=tbContentServiceImpl.save(content);
	if(index>0){
	    er.setStatus(200);
	}
	return er;
    }
}
