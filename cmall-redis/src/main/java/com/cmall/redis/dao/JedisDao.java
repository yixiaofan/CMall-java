package com.cmall.redis.dao;

public interface JedisDao {
    /*
     * 判断key是否存在
     */
    Boolean exists(String key);
    
    /*
     * 删除
     */
    Long del(String key);
    
    /*
     * 设置值
     */
    String set(String key,String value);
    
    /*
     * 取值
     */
    String get(String key);
    
    /*
     * 设置key的过期时间
     */
    Long expire(String key,int seconds);
    
    /*
     * 自增+1
     */
    Long incr(String key);
    
    /*
     * 减少num个
     */
    Long decr(String key,Long integer);
}
