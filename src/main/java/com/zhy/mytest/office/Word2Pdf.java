package com.zhy.mytest.office;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;

/**
 * @author: zhy
 * @Date:2020/11/27
 * @Description:
 **/
public class Word2Pdf {

    public static void main(String[] args) {

        //加载Word示例文档
        Document document = new Document();
        document.loadFromFile("C:\\Users\\Administrator\\Desktop\\test.doc");
        // 保存结果文档
        document.saveToFile("C:\\Users\\Administrator\\Desktop\\test.pdf", FileFormat.PDF);
        document.close();
    }
}
