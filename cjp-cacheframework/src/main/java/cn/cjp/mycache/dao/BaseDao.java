package cn.cjp.mycache.dao;

import java.io.Serializable;
import java.util.List;

import cn.cjp.mycache.bean.PageableBean;

public interface BaseDao<T> {

	public T save(T obj);
	
	public T findOne(Serializable id);
	
	public List<T> findAll(PageableBean pageable);
	
	
}
