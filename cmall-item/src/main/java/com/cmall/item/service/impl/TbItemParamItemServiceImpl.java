package com.cmall.item.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cmall.item.pojo.ParamItem;
import com.cmall.item.service.TbItemParamItemService;
import com.cmall.commons.utils.JsonUtils;
import com.cmall.dubbo.service.TbItemParamItemDubboService;
import com.cmall.pojo.TbItemParamItem;

@Service
public class TbItemParamItemServiceImpl implements TbItemParamItemService {
    @Reference
    private TbItemParamItemDubboService tbItemParamItemDubboServiceImpl;
    
    @Override
    public String showParam(long itemId) {
	TbItemParamItem item=tbItemParamItemDubboServiceImpl.selByItemid(itemId);
	List<ParamItem> list=JsonUtils.jsonToList(item.getParamData(), ParamItem.class);
	StringBuffer sf=new StringBuffer();
	for(ParamItem param:list){
	    sf.append("<table width='500' style='color:gray;'>");
	    for(int i=0;i<param.getParams().size();i++){
		if(i==0){
		    sf.append("<tr>");
		    sf.append("<td style='width:110px;text-align:right;'>"+param.getGroup()+"</td>");
		    sf.append("<td style='width:160px;text-align:right;'>"+param.getParams().get(i).getK()+"</td>");
		    sf.append("<td style='width:160px;text-align:right;'>"+param.getParams().get(i).getV()+"</td>");
		    sf.append("</tr>");
		}else{
		    sf.append("<tr>");
		    sf.append("<td> </td>");
		    sf.append("<td style='text-align:right;'>"+param.getParams().get(i).getK()+"</td>");
		    sf.append("<td style='text-align:right;'>"+param.getParams().get(i).getV()+"</td>");
		    sf.append("</tr>");
		}
	    }
	    sf.append("</table>");
	    sf.append("<hr style='color:gray;'/>");
	}
	return sf.toString();
    }

}
