package com.liuzhixin.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Before;
import org.junit.Test;


import java.io.File;
import java.io.IOException;

public class SearchIndex {
        private IndexSearcher indexSearcher;
        private IndexReader indexReader;

    @Before
    public void init() throws Exception {
        indexReader = DirectoryReader.open(FSDirectory.open(new File("C:\\temp\\index").toPath()));
        indexSearcher = new IndexSearcher(indexReader);
    }

//    @Test
//    public void SearchIndex() throws IOException {
//        // 第一步：创建一个Directory对象，也就是索引库存放的位置.
//        Directory directory = FSDirectory.open(new File("C:\\temp\\index").toPath());
//
//        // 第二步：创建一个indexReader对象，需要指定Directory对象。
//        IndexReader indexReader = DirectoryReader.open(directory);
//
//        // 第三步：创建一个indexsearcher对象，需要指定IndexReader对象
//        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
//        // 第四步：创建一个TermQuery对象，指定查询的域和查询的关键词。
//        Query query = new TermQuery(new Term("content","lucene"));
//        // 第五步：执行查询。
//
//        TopDocs search = indexSearcher.search(query, 10);
//
//        System.out.println("查询的总条数是"+search.totalHits);
//        // 第六步：返回查询结果。遍历查询结果并输出。
//
//        for (ScoreDoc scoreDoc : search.scoreDocs) {
//
//           Document document = indexSearcher.doc(scoreDoc.doc);
//
//            System.out.println(document.get("filename"));
//            //System.out.println(document.get("fileName"));
//            System.out.println(document.get("path"));
//            System.out.println(document.get("size"));
//        }
//        // 第七步：关闭IndexReader对象
//
//        indexReader.close();
//    }

    @Test
    public void testRangeQuery() throws Exception {

        Query query  = LongPoint.newRangeQuery("size",01,10001);

        printResult(query);

    }

    private void printResult(Query query) throws Exception {
        //执行查询
        TopDocs topDocs = indexSearcher.search(query, 10);
        System.out.println("总记录数：" + topDocs.totalHits);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc doc:scoreDocs){
            //取文档id
            int docId = doc.doc;
            //根据id取文档对象
            Document document = indexSearcher.doc(docId);
            System.out.println(document.get("name"));
            System.out.println(document.get("path"));
            System.out.println(document.get("size"));
            //System.out.println(document.get("content"));
            System.out.println("-----------------寂寞的分割线");
        }
        indexReader.close();
    }

}
