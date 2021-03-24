package com.zhy.mytest.office;

import com.spire.doc.*;

public class MergeWord2 {

    //新起一页合并Word文档
    public static void main(String[] args) {
        // 加载文档1
        Document doc1 = new Document("C:\\Users\\Administrator\\Desktop\\aa.doc");
        // 调用方法将文档2插入到文档1
        doc1.insertTextFromFile("C:\\Users\\Administrator\\Desktop\\bb.doc", FileFormat.Docx_2010);
        doc1.insertTextFromFile("C:\\Users\\Administrator\\Desktop\\cc.doc", FileFormat.Docx_2010);
        //保存合并后的文档
        doc1.saveToFile("C:\\Users\\Administrator\\Desktop\\abc.doc",FileFormat.Docx_2010);
    }
}