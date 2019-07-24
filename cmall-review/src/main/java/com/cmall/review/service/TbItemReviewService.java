package com.cmall.review.service;

import java.util.List;
import com.cmall.commons.pojo.EasyUIDataGrid;
import com.cmall.pojo.TbItemReview;

public interface TbItemReviewService {
    /*
     * 添加评论
     */
    Boolean insReview(List<TbItemReview> itemReviews);
    
    /*
     * 根据id分页查询评论
     */
    EasyUIDataGrid showItemReview(String itemid,int page,int rows);
}
