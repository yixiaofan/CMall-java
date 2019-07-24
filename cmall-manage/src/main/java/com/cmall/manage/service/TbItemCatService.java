package com.cmall.manage.service;

import java.util.List;
import com.cmall.commons.pojo.EasyUiTree;

public interface TbItemCatService {
    /*
     * 根据父菜单id显示所有子菜单
     */
    List<EasyUiTree> show(long pid);
}
