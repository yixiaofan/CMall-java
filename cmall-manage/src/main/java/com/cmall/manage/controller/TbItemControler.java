package com.cmall.manage.controller;

import javax.annotation.Resource;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmall.manage.service.TbItemService;
import com.cmall.commons.pojo.EasyUIDataGrid;
import com.cmall.commons.utils.JsonUtils;
import com.cmall.commons.pojo.CmallResult;
import com.cmall.pojo.TbItem;

@Controller
public class TbItemControler {
    @Resource
    private TbItemService tbItemServiceIpl;
    /*
     * 分页显示商品
     */
    @RequestMapping("item/list")
    @ResponseBody
    public MappingJacksonValue show(int page,int rows){
	MappingJacksonValue mjv=new MappingJacksonValue(tbItemServiceIpl.show(page, rows));
	mjv.setJsonpFunction("");
	return mjv;
    }
    
    /*
     * 商品删除
     */
    @RequestMapping("rest/item/delete")
    @ResponseBody
    public CmallResult delete(String ids){
	CmallResult er=new CmallResult();
	int index=tbItemServiceIpl.update(ids, (byte)3);
	if(index==1){
	    er.setStatus(200);
	}
	return er;
    }
    
    /*
     * 商品上架
     */
    @RequestMapping("rest/item/reshelf")
    @ResponseBody
    public CmallResult reshelf(String ids){
	CmallResult er=new CmallResult();
	int index=tbItemServiceIpl.update(ids, (byte)1);
	if(index==1){
	    er.setStatus(200);
	}
	return er;
    }
    
    /*
     * 商品下架
     */
    @RequestMapping("rest/item/instock")
    @ResponseBody
    public CmallResult instock(String ids){
	CmallResult er=new CmallResult();
	int index=tbItemServiceIpl.update(ids, (byte)2);
	if(index==1){
	    er.setStatus(200);
	}
	return er;
    }
    
    /*
     * 商品新增
     */
    @RequestMapping("item/save")
    @ResponseBody
    public CmallResult insert(String itemStr,String desc,String itemParams){
	CmallResult er=new CmallResult();
	TbItem item=JsonUtils.jsonToPojo(itemStr, TbItem.class);
	int index;
	try {
	    index=tbItemServiceIpl.save(item, desc, itemParams);
	    if(index==1){
	        er.setStatus(200);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return er;
    }
}
