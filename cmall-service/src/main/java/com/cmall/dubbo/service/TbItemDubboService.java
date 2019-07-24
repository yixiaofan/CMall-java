package com.cmall.dubbo.service;

import java.util.List;

import com.cmall.commons.pojo.EasyUIDataGrid;
import com.cmall.pojo.TbItem;
import com.cmall.pojo.TbItemDesc;
import com.cmall.pojo.TbItemParamItem;

public interface TbItemDubboService {
    /*
     * 商品分页查询
     */
    EasyUIDataGrid show(int page,int rows);
    
    /*
     * 根据id修改状态
     */
    int updItemStatus(TbItem tbItem);
    
    /*
     * 商品新增
     */
    int insTbItem(TbItem tbItem);
    
    /*
     * 新增包含商品表和商品描述表
     */
    int insTbItemDesc(TbItem tbItem,TbItemDesc desc,TbItemParamItem paramItem) throws Exception;
    
    /*
     * 通过状态查询全部可用数据
     */
    List<TbItem> selAllByStatus(byte status);
    
    /*
     * 根据主键查询
     */
    TbItem selById(long id);
}
