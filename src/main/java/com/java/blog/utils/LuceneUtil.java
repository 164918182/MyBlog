package com.java.blog.utils;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.java.blog.entity.Blog;

import lombok.extern.log4j.Log4j;

/**@author TaoYu
 * @Description LUCENE工具类，不支持并发
 * @Date: 2016年6月16日下午9:34:19 
 */
@Log4j
public class LuceneUtil {
	private static final String PATH = "E://lucene";// 默认的索引存储地址
	private static Directory dir;// 存储地目录
	private static IndexWriterConfig iwc;// 写索引配置文件
	private static Analyzer analyzer;// 分词器
	static {
		try {
			dir = FSDirectory.open(Paths.get(PATH));// 初始化目录
			analyzer = new SmartChineseAnalyzer();// 初始化中文分词器，可以更换为其他的
			iwc = new IndexWriterConfig(analyzer);// 初始化写索引配置文件
		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
		}
	}

	/**@Describe：得到写索引实例
	 * @Date： 2016年6月16日下午9:34:23
	 * @return
	 */
	private static IndexWriter getWriter() {
		IndexWriter writer = null;
		try {
			writer = new IndexWriter(dir, iwc);
		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
		}
		return writer;
	}

	/**@Describe：添加索引
	 * @Date： 2016年6月16日下午9:37:52
	 * @param blog
	 * @throws IOException 
	 */
	public static void addIndex(Blog blog) throws IOException {
		IndexWriter writer = getWriter();
		Document doc = new Document();
		doc.add(new IntField("id", blog.getId(), Store.YES));
		doc.add(new TextField("title", blog.getTitle(), Store.YES));
		doc.add(new StringField("releaseDate", DateFormatUtils.format(new Date(), "yyyy-MM-dd"), Store.NO));
		doc.add(new TextField("content", blog.getContent(), Store.YES));
		writer.addDocument(doc);
		writer.close();
	}

	/**@Describe：更新索引
	 * @Date： 2016年6月16日下午9:39:15
	 * @param blog
	 * @throws IOException 
	 * @throws Exception
	 */
	public void updateIndex(Blog blog) throws IOException {
		IndexWriter writer = getWriter();
		Document doc = new Document();
		doc.add(new StringField("id", String.valueOf(blog.getId()), Store.YES));
		doc.add(new TextField("title", blog.getTitle(), Store.YES));
		doc.add(new StringField("releaseDate", DateFormatUtils.format(new Date(), "yyyy-MM-dd"), Store.NO));
		doc.add(new TextField("content", blog.getContent(), Store.YES));
		writer.updateDocument(new Term("id", String.valueOf(blog.getId())), doc);
		writer.close();

	}

	/**@Describe：删除索引
	 * @Date： 2016年6月16日下午9:45:05
	 * @param blogId
	 * @throws IOException
	 */
	public void deleteIndex(String blogId) throws IOException {
		IndexWriter writer = getWriter();
		writer.deleteDocuments(new Term("id", blogId));
		writer.forceMergeDeletes();// 真正删除(好像有效率问题)TODO 改天详细看一下
		writer.commit();
		writer.close();
	}

	/**@Describe：查询索引博客（里面一些可以抽出来，为了流程完整暂不处理）
	 * @Date： 2016年6月16日下午9:45:35
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public static List<Blog> searchBlog(String param, Integer nums) throws Exception {
		// 1打开度索引流
		IndexReader reader = DirectoryReader.open(dir);
		IndexSearcher is = new IndexSearcher(reader);
		// 2构建组合查询并添加二个查询器
		BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();// 组合查询
		QueryParser titleParser = new QueryParser("title", analyzer);
		Query titleQuery = titleParser.parse(param);
		QueryParser contentParser = new QueryParser("content", analyzer);
		Query contentQuery = contentParser.parse(param);
		booleanQuery.add(titleQuery, BooleanClause.Occur.SHOULD);
		booleanQuery.add(contentQuery, BooleanClause.Occur.SHOULD);
		// 3构建高亮插件
		QueryScorer scorer = new QueryScorer(titleQuery);// 以标题构建
		Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);// 简单高亮插件
		SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><mark>", "</mark></b>");// 设置返回结果
		Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);
		highlighter.setTextFragmenter(fragmenter);
		// 4初始化返回的集合，并通过条件查询搜索得到命中的文档
		List<Blog> blogList = new LinkedList<Blog>();
		TopDocs hits = is.search(booleanQuery.build(), nums);
		// 5遍历命中的文档
		for (ScoreDoc scoreDoc : hits.scoreDocs) {
			Blog blog = new Blog();
			Document doc = is.doc(scoreDoc.doc);// 什么鬼设计（显示的调用ID，不友好差评）
			blog.setId(Integer.parseInt(doc.get(("id"))));
			blog.setReleaseDate(DateUtils.parseDate(doc.get(("releaseDate")), "yyyy-MM-dd"));
			String title = doc.get("title");
			String content = StringEscapeUtils.escapeHtml(doc.get("content").substring(0, 100));
			// 设置返回标题高亮
			if (title != null) {
				TokenStream tokenStream = analyzer.tokenStream("title", new StringReader(title));
				String hTitle = highlighter.getBestFragment(tokenStream, title);
				if (StringUtils.isEmpty(hTitle)) {
					blog.setTitle(title);
				} else {
					blog.setTitle(hTitle);
				}
			}
			// 设置返回内容高亮
			if (content != null) {
				TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(content));
				String hContent = highlighter.getBestFragment(tokenStream, content);
				if (StringUtils.isEmpty(hContent)) {
					if (content.length() <= 200) {
						blog.setContent(content);
					} else {
						blog.setContent(content.substring(0, 200));
					}
				} else {
					blog.setContent(hContent);
				}
			}
			blogList.add(blog);
		}
		return blogList;
	}
}
