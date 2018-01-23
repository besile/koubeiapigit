package com.koubei.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class com_TypeHelper {
    /**
     * 判断是否是集合
     * @param o
     * @return
     */
    public static boolean isCollection(Object o) {
        Class<?> type = o.getClass();
        boolean b = (type.isArray())
                || Iterable.class.equals(type)
                || Collection.class.isAssignableFrom(type)
                || (o instanceof List)
                || (o instanceof Collection);
        return b;
    }

    /**
     * 判断是否是map
     * @param o
     * @return
     */
    public static boolean isMap(Object o){
        Class<?> type=o.getClass();
        return Map.class.isAssignableFrom(type);
    }
}
