package s.indexer.iao;

import java.util.List;

import org.apache.lucene.index.Term;

//索引访问对象--基础接口：增删改查
public interface BaseIao<T> {
	/**
	 * 增量索引:incrementIndex
	 * @param o
	 * @return
	 */
	public int incIndex(List<T> list);
	public int incIndex(T o);
	public int delIndex(T o);
	/**
	 * 清空索引
	 * @return
	 */
	public int clsIndex();
	public int updateIndex(T o);
	/**
	 * 检索
	 * @param query 遵守lucene query bool规则
	 * @param fields 指定在哪些域进行搜索
	 * @return
	 */
	public List<T> query(String query,String ...fields);
	public List<T> query(Term ...term);
}
