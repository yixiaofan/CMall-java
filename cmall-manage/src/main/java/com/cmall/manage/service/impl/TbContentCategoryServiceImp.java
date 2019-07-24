package com.cmall.manage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.alibaba.dubbo.config.annotation.Reference;
import com.cmall.manage.service.TbContentCategoryService;
import com.cmall.commons.pojo.EasyUiTree;
import com.cmall.commons.pojo.CmallResult;
import com.cmall.commons.utils.IDUtils;
import com.cmall.dubbo.service.TbContentCategoryDubboService;
import com.cmall.pojo.TbContentCategory;

@Service
public class TbContentCategoryServiceImp implements TbContentCategoryService {
    @Reference
    private TbContentCategoryDubboService tbContentCategoryDubboServiceImpl;
    @Override
    public List<EasyUiTree> showCategory(long id) {
	List<EasyUiTree> listTree=new ArrayList<EasyUiTree>();
	List<TbContentCategory> list=tbContentCategoryDubboServiceImpl.selByPid(id);
	for(TbContentCategory cate:list){
	    EasyUiTree tree=new EasyUiTree();
	    tree.setId(cate.getId());
	    tree.setText(cate.getName());
	    tree.setState(cate.getIsParent()?"closed":"open");
	    listTree.add(tree);
	}
	return listTree;
    }
    @Override
    public CmallResult create(TbContentCategory cate) {
	CmallResult er=new CmallResult();
	//判断当前节点名称是否已经存在
	List<TbContentCategory> children=tbContentCategoryDubboServiceImpl.selByPid(cate.getParentId());
	for(TbContentCategory child:children){
	    if(child.getName().equals(cate.getName())){
		return er;
	    }
	}
	Date date=new Date();
	cate.setCreated(date);
	cate.setUpdated(date);
	cate.setStatus(1);
	cate.setSortOrder(1);
	cate.setIsParent(false);
	long id=IDUtils.genItemId();
	cate.setId(id);
	int index=tbContentCategoryDubboServiceImpl.insTbContentCategory(cate);
	if(index>0){
	    TbContentCategory parent=new TbContentCategory();
	    parent.setId(cate.getParentId());
	    parent.setIsParent(true);
	    tbContentCategoryDubboServiceImpl.updIsParentById(parent);
	}
	er.setStatus(200);
	Map<String,Long> map=new HashMap<>();
	map.put("id", id);
	er.setData(map);
	return er;
    }
    @Override
    public CmallResult update(TbContentCategory cate) {
	CmallResult er=new CmallResult();
	//查询当前节点信息
	TbContentCategory cateSelect=tbContentCategoryDubboServiceImpl.selById(cate.getId());
	//查询当前节点的父节点的所有子节点信息
	List<TbContentCategory> children=tbContentCategoryDubboServiceImpl.selByPid(cateSelect.getParentId());
	for(TbContentCategory child:children){
	    if(child.getName().equals(cate.getName())){
		er.setData("该分类名已存在");
		return er;
	    }
	}
	cate.setUpdated(new Date());
	int index=tbContentCategoryDubboServiceImpl.updIsParentById(cate);
	if(index>0){
	    er.setStatus(200);
	}
	return er;
    }
    @Override
    public CmallResult delete(TbContentCategory cate) {
	CmallResult er=new CmallResult();
	cate.setStatus(0);
	Date date=new Date();
	cate.setUpdated(date);
	int index=tbContentCategoryDubboServiceImpl.updIsParentById(cate);
	if(index>0){
	    TbContentCategory curr=tbContentCategoryDubboServiceImpl.selById(cate.getId());
	    List<TbContentCategory> list=tbContentCategoryDubboServiceImpl.selByPid(curr.getParentId());
	    if(list==null||list.size()==0){
		TbContentCategory parent=new TbContentCategory();
		parent.setId(curr.getParentId());
		parent.setIsParent(false);
		parent.setUpdated(date);
		int result=tbContentCategoryDubboServiceImpl.updIsParentById(parent);
		if(result>0){
		    er.setStatus(200);
		}
	    }else{
		er.setStatus(200);
	    }
	}
	return er;
    }

}
