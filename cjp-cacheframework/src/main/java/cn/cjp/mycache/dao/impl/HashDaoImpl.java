package cn.cjp.mycache.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import cn.cjp.mycache.bean.PageableBean;
import cn.cjp.mycache.dao.BaseDao;
import cn.cjp.mycache.domain.BaseDomain;

public class HashDaoImpl implements BaseDao<BaseDomain> {

	/**
	 * 模拟缓存数据库
	 */
	private Map<Object, BaseDomain> database = new HashMap<Object, BaseDomain>();

	public BaseDomain save(BaseDomain obj) {
		if (null == obj.getId()) {
			System.out.println("dao.save");
			obj.setId(UUID.randomUUID().toString());
		}else{
			System.out.println("dao.update");
		}
		database.put(obj.getId(), obj);
		return obj;
	}

	public BaseDomain findOne(Serializable id) {
		System.out.println("dao.findOne");
		return database.get(id);
	}

	public List<BaseDomain> findAll(PageableBean pageable) {
		throw new NotImplementedException();
	}

}
