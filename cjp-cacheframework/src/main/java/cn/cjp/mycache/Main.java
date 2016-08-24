package cn.cjp.mycache;

import cn.cjp.mycache.advice.CacheAdvice;
import cn.cjp.mycache.advice.impl.CacheAdviceImpl;
import cn.cjp.mycache.cache.impl.MapCacheDaoImpl;
import cn.cjp.mycache.dao.BaseDao;
import cn.cjp.mycache.dao.impl.HashDaoImpl;
import cn.cjp.mycache.domain.PeopleDomain;

public class Main {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		CacheAdvice cacheAdvice = new CacheAdviceImpl();
		cacheAdvice.setCacheDao(new MapCacheDaoImpl());

		final HashDaoImpl target = new HashDaoImpl();

		BaseDao<PeopleDomain> proxyBaseDao = (BaseDao<PeopleDomain>) new CacheProxyFactory(cacheAdvice, target)
				.getProxy();
		
		PeopleDomain people = new PeopleDomain();
		people.setName("Jack");
		
		PeopleDomain savedPeople = proxyBaseDao.save(people);
		System.out.println("--------------");
		
		people = proxyBaseDao.findOne(savedPeople.getId());
		System.out.println("--------------");
		
		people = proxyBaseDao.findOne(savedPeople.getId());
		System.out.println("--------------");
		
	}

}
