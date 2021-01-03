package xyz.zerxoi.utils;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class WebUtils {
    public static <T> T copyParamToBean(T bean, Map<String, String[]> map) {
        try {
            BeanUtils.populate(bean, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
}
