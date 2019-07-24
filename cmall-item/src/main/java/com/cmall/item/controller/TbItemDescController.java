package com.cmall.item.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cmall.item.service.TbItemDescService;

@Controller
public class TbItemDescController {
    @Resource
    private TbItemDescService tbItemDescServiceImpl;
    
    /*
     * 显示商品详情
     */
    @RequestMapping(value="item/desc/{id}",produces="text/html;charset=utf-8")
    @ResponseBody
    public String desc(@PathVariable long id){
	return tbItemDescServiceImpl.showDesc(id);
    }
}
