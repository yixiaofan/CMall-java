package com.cmall.manage.service;

import com.cmall.commons.pojo.EasyUIDataGrid;
import com.cmall.pojo.TbItem;

public interface TbItemService {
    /*
     * 显示商品
     */
    EasyUIDataGrid show(int page,int rows);
    
    /*
     * 批量修改商品状态
     */
    int update(String ids,byte status);
    
    /*
     * 商品新增
     */
    int save(TbItem item,String desc,String itemParams) throws Exception;
}
