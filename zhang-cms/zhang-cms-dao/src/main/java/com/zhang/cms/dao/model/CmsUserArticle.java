package com.zhang.cms.dao.model;

import java.io.Serializable;

public class CmsUserArticle implements Serializable {
    /**
     * 
     *
     * @mbg.generated
     */
    private Integer userArticleId;

    /**
     * 
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     * 
     *
     * @mbg.generated
     */
    private Integer articleId;

    private static final long serialVersionUID = 1L;

    public Integer getUserArticleId() {
        return userArticleId;
    }

    public void setUserArticleId(Integer userArticleId) {
        this.userArticleId = userArticleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userArticleId=").append(userArticleId);
        sb.append(", userId=").append(userId);
        sb.append(", articleId=").append(articleId);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CmsUserArticle other = (CmsUserArticle) that;
        return (this.getUserArticleId() == null ? other.getUserArticleId() == null : this.getUserArticleId().equals(other.getUserArticleId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getArticleId() == null ? other.getArticleId() == null : this.getArticleId().equals(other.getArticleId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserArticleId() == null) ? 0 : getUserArticleId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getArticleId() == null) ? 0 : getArticleId().hashCode());
        return result;
    }
}