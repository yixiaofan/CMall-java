package com.cmall.dubbo.service;

import com.cmall.commons.pojo.EasyUIDataGrid;
import com.cmall.pojo.TbItemReview;

public interface TbItemReviewDubboService {
    
    /*
     * 添加评论
     */
    int insReview(TbItemReview itemReview);
    
    /*
     * 评论分页查询
     */
    EasyUIDataGrid showItemReview(String itemid,int page,int rows);
}
