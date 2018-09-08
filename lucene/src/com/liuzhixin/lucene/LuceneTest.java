package com.liuzhixin.lucene;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

import static org.apache.lucene.document.Field.*;

public class LuceneTest {

    @Test
    public void luceneTest() throws IOException {
        //创建索引库的储存路径

        Directory directory = FSDirectory.open(new File("C:\\temp\\index").toPath());

        //基于Directory创建一个indexWriter
        // 分析关键字
        IndexWriterConfig config = new IndexWriterConfig();

        IndexWriter indexWriter = new IndexWriter(directory,config);
        //配置原始文档的
        File file = new File("C:\\temp\\searchsource");

        for (File f : file.listFiles()) {
            String fileName = f.getName();

            String fileContent = FileUtils.readFileToString(f);

            String filePath = f.getPath();

            long fileSize = FileUtils.sizeOf(f);

            //创建文件名域

            Field fileNameField = new TextField("filename",fileName, Store.YES);

            Field fileContentField = new TextField("content",fileContent,Store.YES);
            Field filePathField = new TextField("path",filePath,Store.YES);
            Field fileSizeField = new TextField("size",fileSize+"",Store.YES);


            //创建document对象 把域放进对象中

            Document document = new Document();
            document.add(fileNameField);
            document.add(fileContentField);
            document.add(filePathField);
            document.add(fileSizeField);

            //创建索引，并写入索引库

            indexWriter.addDocument(document);

        }

        indexWriter.close();
    }


    public void addDocument() throws IOException {

        Directory directory = FSDirectory.open(new File("C:\\temp\\index").toPath());

        IndexWriterConfig config = new IndexWriterConfig(new IKAnalyzer());

        //创建一个indexWriter对象
        IndexWriter indexWriter = new IndexWriter(directory, config);

        Document document = new Document();


        document.add(new TextField("filename","新添加的文档",Store.YES));
        document.add(new TextField("content","新添加的文档内容",Store.YES));

        document.add(new LongPoint("size",10001));

        document.add(new StoredField("size",10001));

        document.add(new StoredField("path","d:/temp/1.txt"));

        indexWriter.addDocument(document);

        indexWriter.close();
    }
}
