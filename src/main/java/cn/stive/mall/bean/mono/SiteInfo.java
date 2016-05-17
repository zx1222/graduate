package cn.stive.mall.bean.mono;

/**
 * Created by dxt on 16/5/13.
 */
public class SiteInfo {
    private long id;
    private String site_name;
    private String site_photo;
    private String site_cover;

    private String site_descript;
    private long user_id;
    private String user_photo;
    private String user_name;

    private int article_count;


    public int getArticle_count() {
        return article_count;
    }

    public void setArticle_count(int article_count) {
        this.article_count = article_count;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getSite_cover() {
        return site_cover;
    }

    public void setSite_cover(String site_cover) {
        this.site_cover = site_cover;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSite_name() {
        return site_name;
    }

    public void setSite_name(String site_name) {
        this.site_name = site_name;
    }

    public String getSite_photo() {
        return site_photo;
    }

    public void setSite_photo(String site_photo) {
        this.site_photo = site_photo;
    }

    public String getSite_descript() {
        return site_descript;
    }

    public void setSite_descript(String site_descript) {
        this.site_descript = site_descript;
    }

    public String getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(String user_photo) {
        this.user_photo = user_photo;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }


}
