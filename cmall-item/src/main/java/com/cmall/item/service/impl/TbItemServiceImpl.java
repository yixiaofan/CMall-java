package com.cmall.item.service.impl;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.alibaba.dubbo.config.annotation.Reference;
import com.cmall.item.service.TbItemService;
import com.cmall.commons.pojo.TbItemChild;
import com.cmall.commons.utils.JsonUtils;
import com.cmall.dubbo.service.TbItemDubboService;
import com.cmall.pojo.TbItem;
import com.cmall.redis.dao.JedisDao;

@Service
public class TbItemServiceImpl implements TbItemService {
    @Reference
    private TbItemDubboService tbItemDubboServiceImpl;
    @Resource
    private JedisDao jedisDaoImpl;
    @Value("${redis.item.key}")
    private String itemKey;
    @Value("${redis.itemCount.key}")
    private String itemCountKey;
    @Value("${redis.itemNum.key}")
    private String itemNumKey;
    @Override
    public TbItemChild show(long id) {
	String key=itemKey+id;
	String numKey=itemNumKey+id;
	String countKey=itemCountKey+id;
	Long count=(long) 1;
	if(jedisDaoImpl.exists(countKey)){
	    count=jedisDaoImpl.incr(countKey);
	}
	if(jedisDaoImpl.exists(key)){
	    String json=jedisDaoImpl.get(key);
	    String num=jedisDaoImpl.get(numKey);
	    if(num!=null){
		if(json!=null&&!json.equals("")){
		    TbItemChild item=JsonUtils.jsonToPojo(json, TbItemChild.class);
		    item.setCid(count);
		    item.setNum(Integer.valueOf(num));
		    return item;
		}
	    }
	}
	TbItem item=tbItemDubboServiceImpl.selById(id);
	TbItemChild child=new TbItemChild();
	child.setId(item.getId());
	child.setTitle(item.getTitle());
	child.setPrice(item.getPrice());
	child.setSellPoint(item.getSellPoint());
	child.setNum(item.getNum());
	child.setCid((long)1);
	child.setImages(item.getImage()!=null&&!item.equals("")?item.getImage().split(","):new String[1]);
	//存到数据库中
	jedisDaoImpl.set(numKey, item.getNum().toString());
	jedisDaoImpl.set(countKey, "1");
	jedisDaoImpl.set(key, JsonUtils.objectToJson(child));
	
	return child;
    }

}
