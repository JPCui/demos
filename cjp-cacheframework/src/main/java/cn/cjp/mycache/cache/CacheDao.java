package cn.cjp.mycache.cache;

import java.io.Serializable;

public interface CacheDao {

	public void save(Serializable id, Object obj);
	
	public Object find(Serializable id);
	
}
