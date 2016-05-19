package cn.stive.mall.bean;

/**
 * Created by dxt on 16/4/17.
 */
public class UserInfo {

    private String userphoto ;
    private String name ;
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserphoto() {
        return userphoto;
    }

    public void setUserphoto(String userphoto) {
        this.userphoto = userphoto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
