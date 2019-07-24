package com.cmall.review.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import com.alibaba.dubbo.config.annotation.Reference;
import com.cmall.commons.pojo.EasyUIDataGrid;
import com.cmall.dubbo.service.TbItemReviewDubboService;
import com.cmall.dubbo.service.TbOrderDubboService;
import com.cmall.pojo.TbItemReview;
import com.cmall.review.service.TbItemReviewService;

@Service
public class TbItemReviewServiceImpl implements TbItemReviewService {
    @Reference
    private TbItemReviewDubboService tbItemReviewDubboServiceImpl;
    @Reference
    private TbOrderDubboService tbOrderDubboServiceImpl;
    
    @Override
    public Boolean insReview(List<TbItemReview> itemReviews) {
	int index=0;
	for(TbItemReview itemReview : itemReviews){
	    Date date=new Date();
	    itemReview.setCreated(date);
	    itemReview.setUpdated(date);
	    index+=tbItemReviewDubboServiceImpl.insReview(itemReview);
	}
	tbOrderDubboServiceImpl.updOrderRate(itemReviews.get(0).getOrderId());
	return index==itemReviews.size();
    }

    @Override
    public EasyUIDataGrid showItemReview(String itemid, int page, int rows) {
	return tbItemReviewDubboServiceImpl.showItemReview(itemid, page, rows);
    }

}
