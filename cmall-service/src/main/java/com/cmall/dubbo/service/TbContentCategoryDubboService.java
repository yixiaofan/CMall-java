package com.cmall.dubbo.service;

import java.util.List;
import com.cmall.pojo.TbContentCategory;

public interface TbContentCategoryDubboService {
    /*
     * 根据父id查询所有子类目
     */
    List<TbContentCategory> selByPid(long id);
    
    /*
     * 新增
     */
    int insTbContentCategory(TbContentCategory cate);
    
    /*
     * 修改isParent通过id
     */
    int updIsParentById(TbContentCategory cate);
    
    /*
     * 通过id查询内容类目详细信息
     */
    TbContentCategory selById(long id);
}
