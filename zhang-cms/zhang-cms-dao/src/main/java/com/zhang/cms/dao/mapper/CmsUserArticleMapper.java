package com.zhang.cms.dao.mapper;

import com.zhang.cms.dao.model.CmsUserArticle;
import com.zhang.cms.dao.model.CmsUserArticleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsUserArticleMapper {
    long countByExample(CmsUserArticleExample example);

    int deleteByExample(CmsUserArticleExample example);

    int deleteByPrimaryKey(Integer userArticleId);

    int insert(CmsUserArticle record);

    int insertSelective(CmsUserArticle record);

    List<CmsUserArticle> selectByExample(CmsUserArticleExample example);

    CmsUserArticle selectByPrimaryKey(Integer userArticleId);

    int updateByExampleSelective(@Param("record") CmsUserArticle record, @Param("example") CmsUserArticleExample example);

    int updateByExample(@Param("record") CmsUserArticle record, @Param("example") CmsUserArticleExample example);

    int updateByPrimaryKeySelective(CmsUserArticle record);

    int updateByPrimaryKey(CmsUserArticle record);
}