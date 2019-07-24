package com.cmall.dubbo.service;

import com.cmall.pojo.TbItemParamItem;

public interface TbItemParamItemDubboService {
    /*
     * 根据商品id查询商品规格参数
     */
    TbItemParamItem selByItemid(long itemId);
}
