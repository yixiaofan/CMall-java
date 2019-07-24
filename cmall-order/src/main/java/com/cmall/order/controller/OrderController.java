package com.cmall.order.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cmall.order.pojo.MyOrderParam;
import com.cmall.order.pojo.PaymentInfo;
import com.cmall.order.service.TbOrderService;
import com.cmall.pojo.TbOrderItem;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.cmall.order.pojo.AlipayConfig;
import com.cmall.commons.pojo.CmallResult;
import com.cmall.commons.pojo.EasyUIDataGrid;
import com.cmall.commons.pojo.TbItemChild;

@Controller
public class OrderController {
    @Resource
    private TbOrderService tbOrderServiceImpl;
    /*
     * 显示确认页面
     */
    @RequestMapping("order/order-cart")
    @ResponseBody
    public List<TbItemChild> showCartOrder(@RequestParam("id") List<Long> ids,HttpServletRequest request){
	return tbOrderServiceImpl.showOrderCart(ids, request);
    }
    
    /*
     * 创建订单
     */
    @RequestMapping("order/create")
    @ResponseBody
    public CmallResult createOrder(@RequestBody MyOrderParam param, HttpServletRequest request){
	return tbOrderServiceImpl.create(param, request);
    }
    
    /*
     * 跳转支付页面
     */
    @RequestMapping(value="order/payment",produces = "text/html; charset=utf-8")
    @ResponseBody
    public String paymentOrder(@RequestBody PaymentInfo info, HttpServletResponse response){
	//System.out.println("开始处理OrderServlet的服务");
	//System.out.println(info);
        String message = "如有什么建议欢迎留言评论";
        //向支付宝发送请求
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
                AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
                AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = info.getOrderId();
        //付款金额，必填
        String total_amount = info.getTotal().toString();
        //订单名称，必填
        String subject = info.getTitle();
        //商品描述，可空
        String body = message;
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\""
                + total_amount + "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
        //        + "\"total_amount\":\""+ total_amount +"\"," 
        //        + "\"subject\":\""+ subject +"\"," 
        //        + "\"body\":\""+ body +"\"," 
        //        + "\"timeout_express\":\"10m\"," 
        //        + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节
        AlipayTradePagePayResponse alipayResponse = null;
        try {
            alipayResponse=alipayClient.pageExecute(alipayRequest);
            //System.out.println(alipayResponse.getBody());
             //System.out.println(alipayResponse.getMsg());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
	return alipayResponse.getBody();
    }
    
    /*
     * 分页显示用户订单列表
     */
    @RequestMapping("order/show")
    @ResponseBody
    public EasyUIDataGrid showOrder(int page, int rows, HttpServletRequest request){
	return tbOrderServiceImpl.showOrder(page, rows, request);
    }
    
    /*
     * 获取订单商品列表
     */
    @RequestMapping("order/orderitem")
    @ResponseBody
    public List<TbOrderItem> showOrderItem(String orderid){
	return tbOrderServiceImpl.showOrderItem(orderid);
    }
}
