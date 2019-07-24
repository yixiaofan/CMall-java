package com.cmall.dubbo.service.impl;

import java.util.List;
import javax.annotation.Resource;

import com.cmall.mapper.TbUserMapper;
import com.cmall.dubbo.service.TbUserDubboService;
import com.cmall.pojo.TbUser;
import com.cmall.pojo.TbUserExample;

public class TbUserDubboServiceImpl implements TbUserDubboService {
    @Resource
    private TbUserMapper tbUserMapper;
    @Override
    public TbUser selByUser(TbUser user) {
	TbUserExample example=new TbUserExample();
	example.createCriteria().andUsernameEqualTo(user.getUsername()).andPasswordEqualTo(user.getPassword());
	List<TbUser> list=tbUserMapper.selectByExample(example);
	if(list!=null&&list.size()>0){
	    return list.get(0);
	}
	return null;
    }
    @Override
    public int updUserInfo(TbUser user) {
	return tbUserMapper.updateByPrimaryKeySelective(user);
    }
    @Override
    public int insUserInfo(TbUser user) {
	return tbUserMapper.insertSelective(user);
    }
    
}
