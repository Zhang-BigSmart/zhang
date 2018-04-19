package com.zhang.cms.rpc.mapper;

import com.zhang.cms.dao.model.CmsArticle;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Edison
 * @ClassName:
 * @Desc: 文章VOMapper
 * @date 2017/8/7
 * @history
 */
public interface CmsArticleExtMapper {

    int up(Integer articleId);

    int down(Integer articleId);

    List<CmsArticle> selectCmsArticle(@Param("categoryId") Integer categoryId, @Param("offset") Integer offset, @Param("limit") Integer limit);

    long countByCategoryId(@Param("categoryId") Integer categoryId);

    List<CmsArticle> selectCmsArticlesByTagId(@Param("tagId") Integer tagId, @Param("offset") Integer offset, @Param("limit") Integer limit);

    long countByTagId(@Param("tagId") Integer tagId);

    //@Select("select * from cms_article ca, cms_user cu where ca.user_id = cu.user_id and cu.user_id = #{userId}")
    List<CmsArticle> selectCmsArticlesByUserId(Integer userId);



}
