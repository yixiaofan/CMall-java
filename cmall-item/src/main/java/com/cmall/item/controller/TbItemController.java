package com.cmall.item.controller;

import javax.annotation.Resource;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmall.item.service.TbItemService;

@Controller
public class TbItemController {
    @Resource
    private TbItemService tbItemServiceImpl;
    
    /*
     * 显示商品详情
     */
    @RequestMapping("item")
    @ResponseBody
    public MappingJacksonValue showItemDetails(long id){
	MappingJacksonValue mjv=new MappingJacksonValue(tbItemServiceImpl.show(id));
	mjv.setJsonpFunction("");
	return mjv;
    }
}
