package com.cmall.dubbo.service.impl;

import java.util.List;
import javax.annotation.Resource;
import com.cmall.commons.pojo.EasyUIDataGrid;
import com.cmall.dubbo.service.TbItemReviewDubboService;
import com.cmall.mapper.TbItemReviewMapper;
import com.cmall.pojo.TbItemReview;
import com.cmall.pojo.TbItemReviewExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class TbItemReviewDubboServiceImpl implements TbItemReviewDubboService {
    @Resource
    private TbItemReviewMapper tbItemReviewMapper;
    
    @Override
    public int insReview(TbItemReview itemReview) {
	return tbItemReviewMapper.insert(itemReview);
    }

    @Override
    public EasyUIDataGrid showItemReview(String itemid, int page, int rows) {
	PageHelper.startPage(page, rows);
	//查询全部
	TbItemReviewExample example=new TbItemReviewExample();
	example.createCriteria().andItemIdEqualTo(itemid);
	example.setOrderByClause("updated desc");
	List<TbItemReview> list=tbItemReviewMapper.selectByExample(example);
	//分页代码
	PageInfo<TbItemReview> pi=new PageInfo<>(list);
	//放入到实体类
	EasyUIDataGrid datagrid=new EasyUIDataGrid();
	datagrid.setRows(pi.getList());
	datagrid.setTotal(pi.getTotal());
	return datagrid;
    }

}
