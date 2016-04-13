package cn.stive.mall.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/1/20.
 */
public class IntrospectorUtil {
    private static IntrospectorUtil ourInstance = new IntrospectorUtil();

    public static IntrospectorUtil getInstance() {
        return ourInstance;
    }

    private IntrospectorUtil() {
    }
    //单例，不会释放
    private Map<Class, List<PropertyDescriptor>> PropertyDescriptorsCache = new HashMap<Class, List<PropertyDescriptor>>();


    public List<PropertyDescriptor> getPropertyDescriptors(Class clazz) {

        List<PropertyDescriptor> descriptors = PropertyDescriptorsCache.get(clazz);

        if (descriptors != null && descriptors.size() > 0) {
            return descriptors;
        }

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);

            PropertyDescriptor[] propertyDescriptorArray = beanInfo.getPropertyDescriptors();

            descriptors = new ArrayList<PropertyDescriptor>();

            for (PropertyDescriptor p : propertyDescriptorArray) {

                if (!"class".equals(p.getName())) {
                    descriptors.add(p);
                }
            }

            PropertyDescriptorsCache.put(clazz, descriptors);

        } catch (IntrospectionException e) {
            e.printStackTrace();
        }

        return descriptors;
    }


}
