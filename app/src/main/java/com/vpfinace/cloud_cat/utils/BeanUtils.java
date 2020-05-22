package com.vpfinace.cloud_cat.utils;

import com.vpfinace.cloud_cat.bean.CatBean;

import java.lang.reflect.Field;

public class BeanUtils {


    /**
     * 复制ean2中的属性给bean1
     *
     * @param toBean
     * @param fromBean
     */
    public static void copy(Object toBean, Object fromBean) {
        Field[] fields2 = fromBean.getClass().getDeclaredFields();

        for (Field field2 : fields2) {
            field2.setAccessible(true);
            try {
                Field field1 = toBean.getClass().getDeclaredField(field2.getName());
                field1.setAccessible(true);
                field1.set(toBean, field2.get(fromBean));
            } catch (Exception e) {
            }

        }

    }

    /**
     * 复制catbean的值,保留view和stogeid不变
     * @param toCatBean
     * @param fromCatBean
     */
    public static void copyBeanWithoutView(CatBean toCatBean,CatBean fromCatBean) {
        CatBean srcFromBean = new CatBean();
        CatBean srcToBean = new CatBean();
        BeanUtils.copy(srcFromBean, fromCatBean);
        BeanUtils.copy(srcToBean, toCatBean);
        //互换值
        BeanUtils.copy(toCatBean, fromCatBean);
        BeanUtils.copy(fromCatBean, srcToBean);
        fromCatBean.setView(srcFromBean.getView());
        toCatBean.setView(srcToBean.getView());
        fromCatBean.setStorageId(srcFromBean.getStorageId());
        toCatBean.setStorageId(srcToBean.getStorageId());
    }

    /**
     * 复制catbean的值,不互换
     * @param toCatBean
     * @param fromCatBean
     */
    public static void onlyCopyBeanWithoutView(CatBean toCatBean,CatBean fromCatBean) {
        CatBean srcFromBean = new CatBean();
        CatBean srcToBean = new CatBean();
        BeanUtils.copy(srcFromBean, fromCatBean);
        BeanUtils.copy(srcToBean, toCatBean);
        //互换值
        BeanUtils.copy(toCatBean, fromCatBean);
//        BeanUtils.copy(fromCatBean, srcToBean);
//        fromCatBean.setView(srcFromBean.getView());
        toCatBean.setView(srcToBean.getView());
//        fromCatBean.setStorageId(srcFromBean.getStorageId());
        toCatBean.setStorageId(srcToBean.getStorageId());
    }
}
