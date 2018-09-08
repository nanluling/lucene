package com.liuzhixin.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;


public class TestTokenStream {
    @Test
    public void testTokenStream() throws IOException {
        //创建一个标准分析器
        Analyzer analyzer = new IKAnalyzer();
        //获得tokenStream对象

        TokenStream tokenStream = analyzer.tokenStream("test","吃枪子把孙子");

        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);

        OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);

        tokenStream.reset();

        while (tokenStream.incrementToken()){

            System.out.println(+offsetAttribute.startOffset());

            System.out.println(charTermAttribute);

            System.out.println(offsetAttribute.endOffset());
        }

        tokenStream.close();
    }


}
