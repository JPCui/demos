package cn.cjp.mycache.cache.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import cn.cjp.mycache.cache.CacheDao;

public class MapCacheDaoImpl implements CacheDao {

	private Map<Serializable, Object> cacheDatabase = new HashMap<Serializable, Object>();

	public void save(Serializable id, Object obj) {
		System.out.println("debug : save or update '" + id + "' to cache");
		cacheDatabase.put(id, obj);
	}

	public Object find(Serializable id) {
		System.out.println("debug : find '" + id + "' from cache");
		Object obj = null;
		if (cacheDatabase.containsKey(id)) {
			obj = cacheDatabase.get(id);
		}
		System.out.println("debug : find " + obj);
		return obj;
	}

}
