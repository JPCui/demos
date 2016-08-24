package s.indexer.bean;

public class News {

	private String id;
	private int classId;
	private String title;
	private String content;
	
	/** 来源 */
	private String urlFrom;
	private String staticUrlFrom;
	
	/** 点击数 */
	private int hits;

	private String TF_IDF;
	
	private String pubDate;
	private String getDate;

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStaticUrlFrom() {
		return staticUrlFrom;
	}

	public void setStaticUrlFrom(String staticUrlFrom) {
		this.staticUrlFrom = staticUrlFrom;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public String getUrlFrom() {
		return urlFrom;
	}

	public void setUrlFrom(String urlFrom) {
		this.urlFrom = urlFrom;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getGetDate() {
		return getDate;
	}

	public void setGetDate(String getDate) {
		this.getDate = getDate;
	}

	public String getTF_IDF() {
		return TF_IDF;
	}

	public void setTF_IDF(String tF_IDF) {
		TF_IDF = tF_IDF;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
