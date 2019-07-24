package com.cmall.manage.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.alibaba.dubbo.config.annotation.Reference;
import com.cmall.manage.service.TbItemService;
import com.cmall.commons.pojo.EasyUIDataGrid;
import com.cmall.commons.utils.HttpClientUtil;
import com.cmall.commons.utils.IDUtils;
import com.cmall.commons.utils.JsonUtils;
import com.cmall.dubbo.service.TbItemDescDubboService;
import com.cmall.dubbo.service.TbItemDubboService;
import com.cmall.pojo.TbItem;
import com.cmall.pojo.TbItemDesc;
import com.cmall.pojo.TbItemParamItem;
import com.cmall.redis.dao.JedisDao;

@Service
public class TbItemServiceImpl implements TbItemService {
    @Reference
    private TbItemDubboService tbItemDubboServiceImpl;
    @Reference
    private TbItemDescDubboService tbItemDescDubboServiceImpl;
    @Value("${search.url}")
    private String url;
    @Resource
    private JedisDao jedisDaoImpl;
    @Value("${redis.item.key}")
    private String itemKey;
    
    @Value("${ftpclient.host}")
    private String host;
    @Value("${ftpclient.port}")
    private int port;
    @Value("${ftpclient.username}")
    private String username;
    @Value("${ftpclient.password}")
    private String password;
    @Value("${ftpclient.basepath}")
    private String basePath;
    @Value("${ftpclient.filepath}")
    private String filePath;
    
    @Override
    public EasyUIDataGrid show(int page, int rows) {
	return tbItemDubboServiceImpl.show(page, rows);
    }
    @Override
    public int update(String ids, byte status) {
	int index=0;
	TbItem item=new TbItem();
	Date date=new Date();
	String[] idsStr=ids.split(",");
	for(String id : idsStr){
	    item.setId(Long.parseLong(id));
	    item.setStatus(status);
	    item.setUpdated(date);
	    index += tbItemDubboServiceImpl.updItemStatus(item);
	    if(status==2||status==3){
		jedisDaoImpl.del(itemKey+id);
	    }
	}
	if(index==idsStr.length){
	    return 1;
	}
	
	return 0;
    }
    @Override
    public int save(TbItem item, String desc,String itemParams) throws Exception {
	final TbItem itemFinal=item;
	final String descFinal=desc;
	//设置商品ID和生成时间
	long id=IDUtils.genItemId();
	item.setId(id);
	Date date=new Date();
	item.setCreated(date);
	item.setUpdated(date);
	item.setStatus((byte)1);
	//设置商品描述
	TbItemDesc itemDesc=new TbItemDesc();
	itemDesc.setItemDesc(desc);
	itemDesc.setItemId(id);
	itemDesc.setCreated(date);
	itemDesc.setUpdated(date);
	//设置商品参数
	TbItemParamItem paramItem=new TbItemParamItem();
	paramItem.setCreated(date);
	paramItem.setUpdated(date);
	paramItem.setItemId(id);
	paramItem.setParamData(itemParams);
	
	int index=0;
	index=tbItemDubboServiceImpl.insTbItemDesc(item, itemDesc, paramItem);
	
	new Thread(){
	    public void run(){
		Map<String,Object> map=new HashMap<>();
		map.put("item", itemFinal);
		map.put("desc", descFinal);
		//使用java代码调用search项目把新增商品通过solr处理
		HttpClientUtil.doPostJson(url, JsonUtils.objectToJson(map));
	    };
	}.start();
	
	return index;
    }

}
