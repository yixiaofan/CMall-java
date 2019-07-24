package com.cmall.manage.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmall.manage.service.TbItemParamService;
import com.cmall.commons.pojo.EasyUIDataGrid;
import com.cmall.commons.pojo.CmallResult;
import com.cmall.pojo.TbItemParam;

@Controller
public class TbItemParamController {
    @Resource
    private TbItemParamService tbItemParamServiceImpl;
    
    /*
     * 规格参数-分页显示
     */
    @RequestMapping("item/param/list")
    @ResponseBody
    public EasyUIDataGrid showPage(int page,int rows){
	return tbItemParamServiceImpl.showPage(page, rows);
    }
    
    /*
     * 批量删除规格参数
     */
    @RequestMapping("item/param/delete")
    @ResponseBody
    public CmallResult delete(String ids){
	CmallResult er=new CmallResult();
	try {
	    int index=tbItemParamServiceImpl.delete(ids);
	    if(index==1){
		er.setStatus(200);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return er;
    }
    
    /*
     * 点击商品类目按钮显示添加分组按钮
     */
    @RequestMapping("item/param/query/itemcatid/{catId}")
    @ResponseBody
    public CmallResult show(@PathVariable long catId){
	return tbItemParamServiceImpl.showParam(catId);
    }
    
    /*
     * 商品类目新增
     */
    @RequestMapping("item/param/save/{catId}")
    @ResponseBody
    public CmallResult save(@RequestBody String paramData,@PathVariable long catId){
	TbItemParam param=new TbItemParam();
	param.setItemCatId(catId);
	param.setParamData(paramData);
	return tbItemParamServiceImpl.save(param);
    }
}
