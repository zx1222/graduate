package cn.stive.mall.bean.mono;

/**
 * Created by dxt on 16/5/15.
 */
public class SiteAbstract {
    private String icon_url;
    private long site_id;
    private String site_name;

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public long getSite_Id() {
        return site_id;
    }

    public void setSite_Id(long id) {
        this.site_id = id;
    }

    public String getSite_name() {
        return site_name;
    }

    public void setSite_name(String site_name) {
        this.site_name = site_name;
    }
}
