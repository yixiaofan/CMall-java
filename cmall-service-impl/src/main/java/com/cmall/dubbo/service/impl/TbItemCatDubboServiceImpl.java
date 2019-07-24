package com.cmall.dubbo.service.impl;

import java.util.List;
import javax.annotation.Resource;
import com.cmall.mapper.TbItemCatMapper;
import com.cmall.dubbo.service.TbItemCatDubboService;
import com.cmall.pojo.TbItemCat;
import com.cmall.pojo.TbItemCatExample;

public class TbItemCatDubboServiceImpl implements TbItemCatDubboService {
    @Resource
    private TbItemCatMapper tbItemCatMapper;
    @Override
    public List<TbItemCat> show(long pid) {
	TbItemCatExample example=new TbItemCatExample();
	example.createCriteria().andParentIdEqualTo(pid);
	return tbItemCatMapper.selectByExample(example);
    }
    @Override
    public TbItemCat selById(long id) {
	return tbItemCatMapper.selectByPrimaryKey(id);
    }

}
