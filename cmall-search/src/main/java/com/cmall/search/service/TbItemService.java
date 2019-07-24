package com.cmall.search.service;

import java.io.IOException;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;

import com.cmall.pojo.TbItem;

public interface TbItemService {
    /*
     * 初始化
     */
    void init() throws SolrServerException, IOException;
    
    /*
     * 分页查询
     */
    Map<String,Object> selByQuery(String query,int page,int rows) throws SolrServerException, IOException;
    
    /*
     * 新增
     */
    int add(Map<String,Object> map,String desc) throws SolrServerException, IOException;
}
