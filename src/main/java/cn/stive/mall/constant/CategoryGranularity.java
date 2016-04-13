package cn.stive.mall.constant;

/**
 * Created by dxt on 16/4/9.
 */
public enum CategoryGranularity {
    Main(1),SubCategory(2),SubCategory2(3),Series(4);
    int v;
    CategoryGranularity(int v){
        this.v = v;
    }

    public int v (){
        return v;
    }
}
