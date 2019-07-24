package com.cmall.item.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmall.item.service.TbItemParamItemService;

@Controller
public class TbItemParamItemController {
    @Resource
    private TbItemParamItemService tbItemParamItemServiceImpl;
    /*
     * 显示规格参数
     */
    @RequestMapping(value="item/param/{id}",produces="text/html;charset=utf-8")
    @ResponseBody
    public String param(@PathVariable long id){
	return tbItemParamItemServiceImpl.showParam(id);
    }
}
