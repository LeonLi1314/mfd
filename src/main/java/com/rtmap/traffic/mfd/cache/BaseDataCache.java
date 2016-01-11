package com.rtmap.traffic.mfd.cache;

import java.util.List;

public class BaseDataCache {
	private static EhcacheUtil util = EhcacheUtil.getInstance();

	@SuppressWarnings("unchecked")
	public static <T> List<T> get(String cacheKey) {
		Object o = util.get(CacheNames.BASE_DATA, cacheKey);

		return (List<T>) o;
	}

	public static <T> void put(String cacheKey, List<T> value) {
		util.put(CacheNames.BASE_DATA, cacheKey, value);
	}
}
