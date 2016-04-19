package cn.stive.mall.bean.mono;

import java.util.Date;

/**
 * Created by dxt on 16/4/19.
 * 转发
 */
public class Forward {
    private Long id;
    private Long article_id;
    private Long visitor_id;
    private Date create_time;
    private Integer status ;

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
