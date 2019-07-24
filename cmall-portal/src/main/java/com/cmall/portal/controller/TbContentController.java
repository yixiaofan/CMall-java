package com.cmall.portal.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmall.portal.service.TbContentService;

@Controller
public class TbContentController {
    @Resource
    private TbContentService tbContentServiceImpl;
    
    @RequestMapping("showBigPic")
    @ResponseBody
    public String showBigPic(){
	return tbContentServiceImpl.showBigPic();
    }
}
