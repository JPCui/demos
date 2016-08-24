package s.indexer.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import s.indexer.bean.News;
import s.indexer.iao.impl.NewsIaoImpl;

public class IndexerTest {

	/**
	 * @param args
	 */
	@Test
	public void testRebuild() {
		new NewsIaoImpl().clsIndex();

		News n1 = new News();
		n1.setId("1");
		n1.setTitle("我是一个标题");
		n1.setContent("我里面有内容哦");

		News n2 = new News();
		n2.setId("2");
		n2.setTitle("我是第二个标题");
		n2.setContent("我是第二个内容");

		News n3 = new News();
		n3.setId("3");
		n3.setTitle("我乃第三标题");
		n3.setContent("我里面空空");

		List<News> newsL = new ArrayList<News>();
		newsL.add(n1);
		newsL.add(n2);
		newsL.add(n3);

		new NewsIaoImpl().incIndex(newsL);
	}

	@Test
	public void testSearch() {
		testRebuild();
		List<News> newsList = new NewsIaoImpl().query("里面", "title",
				"content");

		display(newsList);

		// java.lang.reflect.Field []fields = News.class.getDeclaredFields();
		// for (Field field : fields) {
		// System.out.println(field.getName());
		// }
	}

	@Test
	public void testUpdate() {
		testRebuild();

		News news = new News();
		news.setId("2");
		news.setTitle("我乃第三标题=====");
		news.setContent("我里面空空擦====");

		System.out.println(new NewsIaoImpl().updateIndex(news));

		List<News> newsList = new NewsIaoImpl().query("我", "title");

		display(newsList);
	}

	@Test
	public void testDel() {
		testRebuild();
		News news = new News();
		news.setId("2");
		new NewsIaoImpl().delIndex(news);

		List<News> newsList = new NewsIaoImpl().query("我", "title");

		display(newsList);
	}

	public void display(List<News> newsList) {
		for (News news2 : newsList) {
			System.out.println(news2.getTitle() + "--" + news2.getContent());
		}
	}

}
