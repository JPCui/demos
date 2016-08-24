package s.indexer.iao;

import java.util.List;

//索引访问对象--基础接口：增删改查
public interface NewsIao<T> extends BaseIao<T>{
	public List<String> queryStaticUrl();
	
}
