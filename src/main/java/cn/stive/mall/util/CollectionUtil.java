package cn.stive.mall.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by dxt on 16/4/10.
 */
public class CollectionUtil {


    public static List asList(String img_url){
        String[] img_array = img_url.split(",");
        List<String> img_list = Arrays.asList(img_array);
        return img_list;

    }

}
