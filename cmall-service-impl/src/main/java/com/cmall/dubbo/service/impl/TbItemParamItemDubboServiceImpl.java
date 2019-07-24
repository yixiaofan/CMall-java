package com.cmall.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.cmall.mapper.TbItemParamItemMapper;
import com.cmall.dubbo.service.TbItemParamItemDubboService;
import com.cmall.pojo.TbItemParamItem;
import com.cmall.pojo.TbItemParamItemExample;

public class TbItemParamItemDubboServiceImpl implements TbItemParamItemDubboService {
    @Resource
    private TbItemParamItemMapper tbItemParamItemMapper;
    @Override
    public TbItemParamItem selByItemid(long itemId) {
	TbItemParamItemExample example=new TbItemParamItemExample();
	example.createCriteria().andItemIdEqualTo(itemId);
	List<TbItemParamItem> list=tbItemParamItemMapper.selectByExampleWithBLOBs(example);
	if(list!=null&&list.size()>0){
	    return list.get(0);
	}
	return null;
    }

}
