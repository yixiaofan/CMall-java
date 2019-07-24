package com.cmall.dubbo.service.impl;

import java.util.List;
import javax.annotation.Resource;

import com.cmall.mapper.TbItemParamMapper;
import com.cmall.commons.pojo.EasyUIDataGrid;
import com.cmall.dubbo.service.TbItemParamDubboService;
import com.cmall.pojo.TbItemParam;
import com.cmall.pojo.TbItemParamExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class TbItemParamDubboServiceImpl implements TbItemParamDubboService {
    @Resource
    private TbItemParamMapper tbItemParamMapper;
    @Override
    public EasyUIDataGrid showPage(int page, int rows) {
	//先设置分页条件
	PageHelper.startPage(page,rows);
	//设置查询的sql语句
	//XXXExample() 设置了什么,相当于在sql中where从句中添加条件
	List<TbItemParam> list=tbItemParamMapper.selectByExampleWithBLOBs(new TbItemParamExample());
	//根据程序员自己缩写的SQL语句结合分页插件产生最终结果,封装到PageInfo
	PageInfo<TbItemParam> pi=new PageInfo<>(list);
	
	//设置方法返回结果
	EasyUIDataGrid datagrid=new EasyUIDataGrid();
	datagrid.setRows(pi.getList());
	datagrid.setTotal(pi.getTotal());
	return datagrid;
    }
    @Override
    public int delByIds(String ids) throws Exception {
	int index=0;
	String[] idStr=ids.split(",");
	for(String id:idStr){
	    index+=tbItemParamMapper.deleteByPrimaryKey(Long.parseLong(id));
	}
	if(index==idStr.length){
	    return 1;
	}else{
	    throw new Exception("删除失败");
	}
    }
    @Override
    public TbItemParam selByCatid(long catId) {
	TbItemParamExample example=new TbItemParamExample();
	example.createCriteria().andItemCatIdEqualTo(catId);
	List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
	if(list!=null&&list.size()>0){
	    //每个类目只能有一个模板
	    return list.get(0);
	}
	return null;
    }
    @Override
    public int insParam(TbItemParam param) {
	return tbItemParamMapper.insertSelective(param);
    }

}
