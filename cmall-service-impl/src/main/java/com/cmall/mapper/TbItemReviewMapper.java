package com.cmall.mapper;

import com.cmall.pojo.TbItemReview;
import com.cmall.pojo.TbItemReviewExample;
import com.cmall.pojo.TbItemReviewKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbItemReviewMapper {
    int countByExample(TbItemReviewExample example);

    int deleteByExample(TbItemReviewExample example);

    int deleteByPrimaryKey(TbItemReviewKey key);

    int insert(TbItemReview record);

    int insertSelective(TbItemReview record);

    List<TbItemReview> selectByExample(TbItemReviewExample example);

    TbItemReview selectByPrimaryKey(TbItemReviewKey key);

    int updateByExampleSelective(@Param("record") TbItemReview record, @Param("example") TbItemReviewExample example);

    int updateByExample(@Param("record") TbItemReview record, @Param("example") TbItemReviewExample example);

    int updateByPrimaryKeySelective(TbItemReview record);

    int updateByPrimaryKey(TbItemReview record);
}