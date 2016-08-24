package s.indexer.iao.impl;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;

public class BaseIaoImpl {

	protected static String INDEX_PATH = "Index";
	protected static Analyzer ANALYZER = new StandardAnalyzer(Version.LUCENE_29);
//	protected static Analyzer ANALYZER = new MMAnalyzer();
}
