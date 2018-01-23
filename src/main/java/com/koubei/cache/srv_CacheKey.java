package com.koubei.cache;

import com.koubei.annotation.Column;

import java.lang.reflect.Field;

public class srv_CacheKey {
   public static String toPrimaryKey(Object o) throws Exception{
	   Class<?> type=o.getClass();
	   Field[] fields=type.getDeclaredFields();
	   for(Field item:fields){
		   item.setAccessible(true); //设置些属性是可以访问的
		   if(item.isAnnotationPresent(Column.class)){
			   Column c=item.getAnnotation(Column.class);
			   if(c.unique()){
				   return item.get(o).toString();
			   }
		   }
	   }
	   return null;
   }
}
