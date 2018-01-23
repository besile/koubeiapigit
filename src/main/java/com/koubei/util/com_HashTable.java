package com.koubei.util;

import java.util.Hashtable;

public class com_HashTable {
	static Hashtable<String, Object> table = new Hashtable<String, Object>();

	public static void put(String key, Object value) {
		table.put(key, value);
	}

	public static Object get(String key) {
		return table.get(key);
	}

}
