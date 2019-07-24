package com.cmall.dubbo.service;

import java.util.List;

import com.cmall.commons.pojo.EasyUIDataGrid;
import com.cmall.pojo.TbContent;

public interface TbContentDubboService {
    /*
     * 分页查询
     */
    EasyUIDataGrid selContentByPage(long categoryId,int page,int rows);
    
    /*
     * 新增
     */
    int insContent(TbContent content);
    
    /*
     * 查询出最近的前n个
     */
    List<TbContent> selByCount(int count,boolean isSort);
}
