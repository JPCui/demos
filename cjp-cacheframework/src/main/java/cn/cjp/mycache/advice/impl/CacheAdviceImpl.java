package cn.cjp.mycache.advice.impl;

import java.io.Serializable;
import java.lang.reflect.Method;

import cn.cjp.mycache.advice.CacheAdvice;
import cn.cjp.mycache.cache.CacheDao;
import cn.cjp.mycache.domain.BaseDomain;

public class CacheAdviceImpl implements CacheAdvice {

	private CacheDao cacheDao;

	public void saveCache(Method method, Object[] args, Object obj) {
		// save : 添加缓存
		if(args != null && obj != null){
			if (method.getName().equalsIgnoreCase("findOne")) {
				String id = "";
				for (int i = 0; i < args.length; i++) {
					Object arg = args[i];
					id += (i == 0 ? "" : "_") + arg.toString();
				}
				System.out.println("cache.save");
				cacheDao.save(id, obj);
			}
		}
	}

	public Object updateOrFindCache(Method method, Object[] args) {
		if (method.getName().equalsIgnoreCase("save")) {
			/**
			 * update : 刷新缓存
			 */
			Serializable id = ((BaseDomain) args[0]).getId();
			if (null != args && args.length == 1) {
				// 获取 getId() 方法
				// ...
				if (null != id) {
					// 刷新该id对应的cache
					System.out.println("cache.update");
					cacheDao.save(id, args[0]);
				}
			}
		} else if (method.getName().equalsIgnoreCase("findOne")) {
			/**
			 * findOne : 查询缓存
			 */
			if (null != args && args.length == 1) {
				Serializable id = (Serializable) args[0];
				if (null != id) {
					System.out.println("cache.find");
					return cacheDao.find(id);
				}
			}
		}
		return null;
	}

	public CacheDao getCacheDao() {
		return cacheDao;
	}

	public void setCacheDao(CacheDao cacheDao) {
		this.cacheDao = cacheDao;
	}

}
