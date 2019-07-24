package com.cmall.search.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cmall.commons.utils.JsonUtils;
import com.cmall.search.service.TbItemService;
import com.cmall.pojo.TbItem;

@Controller
public class TbItemController {
    @Resource
    private TbItemService tbItemServiceImpl;
    
    /*
     * 初始化
     */
    @RequestMapping(value="solr/init",produces="text/html;charset=utf-8")
    @ResponseBody
    public String init(){
	long start=System.currentTimeMillis();
	try {
	    tbItemServiceImpl.init();
	    long end=System.currentTimeMillis();
	    return "初始化总时间："+(end-start)/1000+"秒";
	} catch (Exception e) {
	    e.printStackTrace();
	    return "初始化失败";
	} 
    }
    
    /*
     * 搜索功能
     */
    @RequestMapping("search")
    @ResponseBody
    public MappingJacksonValue search(String q,@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="30") int rows){
	Map<String, Object> data = new HashMap<String, Object>();
	try {
	    Map<String,Object> map=tbItemServiceImpl.selByQuery(q, page, rows);
	    data.put("itemList", map.get("itemList"));
	    data.put("totalPages", map.get("totalPages"));
	} catch (Exception e) {
	    e.printStackTrace();
	}
	MappingJacksonValue mjv=new MappingJacksonValue(data);
	mjv.setJsonpFunction("");
	return mjv;
    }
    
    /*
     * 新增
     */
    @RequestMapping("solr/add")
    @ResponseBody
    public int add(@RequestBody Map<String,Object> map){
	try {
	    return tbItemServiceImpl.add((LinkedHashMap)map.get("item"),map.get("desc").toString());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return 0;
    }
}
