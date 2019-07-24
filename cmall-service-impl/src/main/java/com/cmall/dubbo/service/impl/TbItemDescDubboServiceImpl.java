package com.cmall.dubbo.service.impl;

import javax.annotation.Resource;

import com.cmall.mapper.TbItemDescMapper;
import com.cmall.dubbo.service.TbItemDescDubboService;
import com.cmall.pojo.TbItemDesc;

public class TbItemDescDubboServiceImpl implements TbItemDescDubboService {
    @Resource
    private TbItemDescMapper tbItemDescMapper;
    @Override
    public int insDesc(TbItemDesc itemDesc) {
	return tbItemDescMapper.insert(itemDesc);
    }
    @Override
    public TbItemDesc selByItemid(long itemid) {
	return tbItemDescMapper.selectByPrimaryKey(itemid);
    }

}
