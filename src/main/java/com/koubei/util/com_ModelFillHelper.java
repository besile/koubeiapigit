package com.koubei.util;

import com.koubei.annotation.Column;
import com.koubei.annotation.RelationKey;

import javax.sql.rowset.CachedRowSet;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class com_ModelFillHelper {
	public static <T> List<T> FillModelList(CachedRowSet rs,
			Class<? extends T> type) throws Exception {
		if (rs == null || rs.size() == 0) {
			return null;
		}
		List<T> list = MatchProperty(rs, type);
		return list;
	}

	public static <T> T FillModel(CachedRowSet rs, Class<? extends T> type)
			throws Exception {
		List<T> list = MatchProperty(rs, type);
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	public static <T> Map<String, T> FillModelMap(CachedRowSet rs,
			Class<? extends T> type) throws Exception {
		Map<String, T> map = new HashMap<String, T>();
		String relationKey = null;
		while (rs.next()) {
			T bean = type.newInstance();
			Field[] fields = type.getDeclaredFields();
			for (Field field : fields) {
				if (field.isAnnotationPresent(Column.class)) {
					Column column = field.getAnnotation(Column.class);
					field.setAccessible(true);

					Object value = rs.getObject(column.name());
					if (value == null || rs.wasNull()) {
						continue;
					}
					field.set(bean, value);
				}
				if (field.isAnnotationPresent(RelationKey.class)) {
					relationKey = field.get(bean).toString();
				}
			}
			if (relationKey == null) {
				return null;
			}
			map.put(relationKey, bean);
			return map;
		}
		return null;
	}

	public static <T> Map<String, List<T>> FillModelMapList(CachedRowSet rs,
			Class<? extends T> type) throws Exception {
		Map<String, List<T>> map = new HashMap<String, List<T>>();
		List<T> list = new ArrayList<T>(rs.size());
		String relationKey = null;
		while (rs.next()) {
			T bean = type.newInstance();
			Field[] fields = type.getDeclaredFields();
			for (Field field : fields) {
				if (field.isAnnotationPresent(Column.class)) {
					Column column = field.getAnnotation(Column.class);
					field.setAccessible(true);

					Object value = rs.getObject(column.name());

					if (value == null || rs.wasNull()) {
						continue;
					}
					field.set(bean, value);
				}
				if (field.isAnnotationPresent(RelationKey.class)) {
					relationKey = field.get(bean).toString();
				}
			}
			list.add(bean);
		}
		if (list == null || list.size() == 0) {
			return null;
		}
		map.put(relationKey, list);
		return map;
	}

	private static <T> List<T> MatchProperty(CachedRowSet rs,
			Class<? extends T> type) throws Exception {
		List<T> list = new ArrayList<T>(rs.size());
		Field[] fields = type.getDeclaredFields();
		while (rs.next()) {
			T bean = type.newInstance();
			for (Field field : fields) {
				if (field.isAnnotationPresent(Column.class)) {
					Column column = field.getAnnotation(Column.class);
					field.setAccessible(true);
					Object value = rs.getObject(column.name());
					if (value == null || rs.wasNull()) {
						continue;
					}
					field.set(bean, value);
				}
			}
			list.add(bean);
		}
		return list;
	}
}
