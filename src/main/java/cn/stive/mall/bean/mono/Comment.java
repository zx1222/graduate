package cn.stive.mall.bean.mono;

import java.util.Date;

/**
 * Created by dxt on 16/4/19.
 */
public class Comment {
    private Long id;
    private Long article_id;
    private Long visitor_id;
    private String comment_content;
    private Date create_time;
    private Integer status ;
    private Long parent_id;

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Long article_id) {
        this.article_id = article_id;
    }

    public Long getVisitor_id() {
        return visitor_id;
    }

    public void setVisitor_id(Long visitor_id) {
        this.visitor_id = visitor_id;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
