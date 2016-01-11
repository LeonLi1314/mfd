package com.rtmap.traffic.mfd.cache;

import java.util.List;

import lqs.frame.ui.model.ComboStrItem;

public class DictCache {
	private static EhcacheUtil util = EhcacheUtil.getInstance();

	@SuppressWarnings("unchecked")
	public static List<ComboStrItem> get(String dictName) {
		Object o = util.get(CacheNames.DICT, dictName);

		return (List<ComboStrItem>) o;
	}

	public static void put(String dictName, List<ComboStrItem> value) {
		util.put(CacheNames.DICT, dictName, value);
	}
}
