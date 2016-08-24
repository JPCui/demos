package cn.cjp.mycache.bean;

/**
 * 分页类
 * @author REAL
 *
 */
public class PageableBean {

	/**
	 * 当前页
	 */
	int pageNum;
	
	/**
	 * 总页数
	 */
	int pageCount;
	
	/**
	 * 上一页
	 */
	int prevPage;
	
	/**
	 * 下一页
	 */
	int nextPage;
	
	public PageableBean(int pageNum){
		this.pageNum = pageNum;
	}
	
	public PageableBean(int pageNum, int pageCount, int prevPage, int nextPage){
		this(pageNum);
		this.pageCount = pageCount;
		this.prevPage = prevPage;
		this.nextPage = nextPage;
	}
	
}
