package com.cmall.review.controller;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cmall.commons.pojo.CmallResult;
import com.cmall.commons.pojo.EasyUIDataGrid;
import com.cmall.pojo.TbItemReview;
import com.cmall.review.service.TbItemReviewService;

@Controller
public class TbItemReviewController {
    @Resource
    private TbItemReviewService tbItemReviewServiceImpl;
    
    /*
     * 添加评论
     */
    @RequestMapping("review/save")
    @ResponseBody
    public CmallResult insert(@RequestBody List<TbItemReview> itemReviews){
	CmallResult er=new CmallResult();
	Boolean flag=tbItemReviewServiceImpl.insReview(itemReviews);
	if(flag){
	    er.setStatus(200);
	}
	return er;
    }
    
    /*
     * 分页显示商品评论
     */
    @RequestMapping("review/list")
    @ResponseBody
    public EasyUIDataGrid show(String itemid, int page, int rows){
	return tbItemReviewServiceImpl.showItemReview(itemid, page, rows);
    }
}
