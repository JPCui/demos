package s.indexer.iao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;

import s.indexer.bean.News;
import s.indexer.iao.NewsIao;

public class NewsIaoImpl extends BaseIaoImpl implements NewsIao<News> {

	private static String NEWS_INDEX_PATH = INDEX_PATH+"\\News";
	@SuppressWarnings("deprecation")
	@Override
	public int incIndex(List<News> newsList)
	{
		for(News news : newsList)
		{
			Document doc = new Document();
	//		doc.add(new Field("id", news.getId(), Store.YES, Index.NOT_ANALYZED));
			//		doc.add(new Field("url", news.getUrlFrom(), Store.YES, Index.ANALYZED));
			doc.add(new Field("id", news.getId(), Store.YES, Index.ANALYZED));
			doc.add(new Field("title", news.getTitle(), Store.YES, Index.ANALYZED));
			doc.add(new Field("content", news.getContent(), Store.YES, Index.ANALYZED));
//			doc.setBoost(10);
	
			// 建立索引
			try {
				IndexWriter indexWriter;
				indexWriter = new IndexWriter(NEWS_INDEX_PATH, ANALYZER, false,
						MaxFieldLength.LIMITED);
				indexWriter.addDocument(doc);
				indexWriter.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 1;
	}


	@SuppressWarnings("deprecation")
	@Override
	public List<News> query(String queryKey,String ...fields) {
		List<News>listNews = new ArrayList<News>();
		//默认搜索全域
		if(fields.length == 0)
		{
			fields = new String[3];
			fields[0] = "title";
			fields[1] = "content";
			fields[2] = "";
		}
		try {
			// 1，把要搜索的文本解析为 Query
			QueryParser queryParser = new MultiFieldQueryParser(fields, ANALYZER);
			Query query = queryParser.parse(queryKey);

			// 2，进行查询
			IndexSearcher indexSearcher = new IndexSearcher(NEWS_INDEX_PATH);
			Filter filter = null;//空过滤器
			TopDocs topDocs = indexSearcher.search(query, filter, 10000);
	
			// 3，转换结果
			for (ScoreDoc scoreDoc : topDocs.scoreDocs)
			{
				int docSn = scoreDoc.doc; // 文档内部编号
				Document doc = indexSearcher.doc(docSn); // 根据编号取出相应的文档
				News news = new News();
				news.setTitle( doc.get("title") );
				news.setContent( doc.get("content") );
				listNews.add(news);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listNews;
	}

	@SuppressWarnings("deprecation")
	@Override
	public int delIndex(News o) {
		Term term = new Term("id", o.getId());
		IndexWriter indexWriter = null;
		try {
			indexWriter = new IndexWriter(NEWS_INDEX_PATH, ANALYZER, MaxFieldLength.LIMITED);
			indexWriter.deleteDocuments(term);
			indexWriter.close();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateIndex(News news) {
		delIndex(news);
		return incIndex(news);
	}


	@Override
	public List<String> queryStaticUrl() {
		return null;
	}


	@SuppressWarnings("deprecation")
	@Override
	public int clsIndex() {
		IndexWriter indexWriter;
		try {
			indexWriter = new IndexWriter(NEWS_INDEX_PATH, ANALYZER, false,
					MaxFieldLength.LIMITED);
			indexWriter.deleteAll();
			indexWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


	@SuppressWarnings("deprecation")
	public List<News> query(Term ...terms) {
		List<News> newsList = null;
		IndexSearcher indexSearcher;
		BooleanQuery query = new BooleanQuery();
		for (Term term : terms)
		{
			query.add(new TermQuery(term),BooleanClause.Occur.MUST);
		}
		try {
			indexSearcher = new IndexSearcher(NEWS_INDEX_PATH);
			Filter filter = null;//空过滤器
			TopDocs topDocs = indexSearcher.search(query, filter, 10000);
			newsList = new ArrayList<News>();
			for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
				int docSn = scoreDoc.doc; // 文档内部编号
				Document doc = indexSearcher.doc(docSn); // 根据编号取出相应的文档
				News news = new News();
				news.setTitle(doc.get("title"));
				news.setContent(doc.get("content"));
				newsList.add(news);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newsList;
	}


	@SuppressWarnings("deprecation")
	@Override
	public int incIndex(News news) {
		List<News>l = query(news.getId(), "id");
		if(l==null || l.size()>0)
		{
			return 0;
		}
		
		Document doc = new Document();
		doc.add(new Field("id", news.getId(), Store.YES, Index.ANALYZED));
		doc.add(new Field("title", news.getTitle(), Store.YES, Index.ANALYZED));
		doc.add(new Field("content", news.getContent(), Store.YES, Index.ANALYZED));
//		doc.setBoost(10);

		// 建立索引
		try {
			IndexWriter indexWriter;
			indexWriter = new IndexWriter(NEWS_INDEX_PATH, ANALYZER, false,
					MaxFieldLength.LIMITED);
			indexWriter.addDocument(doc);
			indexWriter.close();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
