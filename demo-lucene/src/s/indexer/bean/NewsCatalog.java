package s.indexer.bean;

public class NewsCatalog {
	private int id;
	/**
	 * 类别标题
	 */
	private String title;
	/**
	 * 类别简介
	 */
	private String summary;
	/**
	 * 热度
	 */
	private int hot;
	/**
	 * 图片存储路径
	 */
	private String picpath;
	/**
	 * 链接路径
	 */
	private String refurl;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPicpath() {
		return picpath;
	}
	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRefurl() {
		return refurl;
	}
	public void setRefurl(String refurl) {
		this.refurl = refurl;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public int getHot() {
		return hot;
	}
	public void setHot(int hot) {
		this.hot = hot;
	}
	
}





