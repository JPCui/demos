package s.indexer.bean;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;

public class BeanUtil {

	public static News Doc2News(Document doc)
	{
		News news = new News();
		news.setId( doc.get("id"));
		news.setId( doc.get("title"));
		news.setId( doc.get("content"));
		return news;
	}

	public static Document News2Doc(News news)
	{
		Document doc = new Document();
		doc.add(new Field("id", news.getId(), Store.YES, Index.ANALYZED));
		doc.add(new Field("title", news.getTitle(), Store.YES, Index.ANALYZED));
		doc.add(new Field("content", news.getContent(), Store.YES, Index.ANALYZED));
		return doc;
	}
	
}
