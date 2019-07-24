package com.cmall.manage.service;

import com.cmall.commons.pojo.EasyUIDataGrid;
import com.cmall.pojo.TbContent;

public interface TbContentService {
    /*
     * 分页显示内容信息
     */
    EasyUIDataGrid showContent(long categoryId,int page,int rows);
    
    /*
     * 新增内容
     */
    int save(TbContent content);
}
