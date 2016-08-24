package cn.cjp.mycache;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import cn.cjp.mycache.advice.CacheAdvice;

public class CacheProxyFactory {


	private CacheAdvice advice;
	private Object target;

	public CacheAdvice getAdvice() {
		return advice;
	}

	public void setAdvice(CacheAdvice advice) {
		this.advice = advice;
	}

	/**
	 * 需要代理的类
	 * 
	 * @return
	 */
	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public CacheProxyFactory(CacheAdvice advice, Object target) {
		this.advice = advice;
		this.target = target;
	}

	public Object getProxy() {
		Object proxy3 = Proxy.newProxyInstance(target.getClass()
				.getClassLoader(), target.getClass().getInterfaces(),
				new InvocationHandler() {
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						// 更新或查找缓存
						Object retVal = advice.updateOrFindCache(method, args);
						System.out.println("ret: "+retVal);
						// 如果是更新操作或查询缓存为空
						if (null == retVal) {
							retVal = method.invoke(target, args);
							advice.saveCache(method, args, retVal);
						}
						return retVal;
					}
				});
		return proxy3;
	}

}
