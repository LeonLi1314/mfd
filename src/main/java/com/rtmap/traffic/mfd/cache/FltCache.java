package com.rtmap.traffic.mfd.cache;

public class FltCache {
	private static EhcacheUtil util = EhcacheUtil.getInstance();

	@SuppressWarnings("unchecked")
	public static <T> T get(String cacheKey) {
		Object o = util.get(CacheNames.FLT_DYNAMIC, cacheKey);

		return (T) o;
	}

	public static  void put(String cacheKey, Object value) {
		util.put(CacheNames.FLT_DYNAMIC, cacheKey, value);
	}
}
