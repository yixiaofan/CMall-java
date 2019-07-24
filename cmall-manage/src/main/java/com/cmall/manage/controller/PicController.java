package com.cmall.manage.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cmall.manage.service.PicService;

@Controller
public class PicController {
    @Resource
    private PicService picServiceImpl;
    
    /*
     * 图片上传
     */
    @RequestMapping("pic/upload")
    @ResponseBody
    public Map<String,Object> upload(List<MultipartFile> uploadFile){
	Map<String,Object> map=new HashMap<>();;System.out.println(uploadFile);
	String url="";
	try {
	    for(int i=0;i<uploadFile.size();i++){
		//System.out.println(uploadFile.get(i));
		Map<String,Object> mymap=new HashMap<>();
		mymap=picServiceImpl.upload(uploadFile.get(i));
		if((int)mymap.get("error")==0){
		    url+=mymap.get("url");
		}else{
		    return mymap;
		}
		if(i<uploadFile.size()-1){
		    url+=",";
		}
	    }
	    map.put("error", 0);
	    map.put("url",url);
	} catch (IOException e) {
	    e.printStackTrace();
	    map.put("error", 1);
	    map.put("message","上传图片时服务器异常");
	}
	return map;
    }
}
