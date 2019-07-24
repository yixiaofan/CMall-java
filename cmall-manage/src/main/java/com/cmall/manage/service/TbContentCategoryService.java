package com.cmall.manage.service;

import java.util.List;
import com.cmall.commons.pojo.EasyUiTree;
import com.cmall.commons.pojo.CmallResult;
import com.cmall.pojo.TbContentCategory;

public interface TbContentCategoryService {
    /*
     * 查询所有类目并转换为easyui tree的属性要求
     */
    List<EasyUiTree> showCategory(long id);
    
    /*
     * 类目新增
     */
    CmallResult create(TbContentCategory cate);
    
    /*
     * 类目重命名
     */
    CmallResult update(TbContentCategory cate);
    
    /*
     * 删除类目
     */
    CmallResult delete(TbContentCategory cate);
}
