package com.zhy.mytest.office;

import  com.spire.doc.*;

public class MergeWord1 {

    //承接上文段落合并Word文档
    public static void main(String[] args){
        // 加载需要合并的两个文档
        Document doc1 = new Document("C:\\Users\\Administrator\\Desktop\\aa.doc");
        Document doc2 = new Document("C:\\Users\\Administrator\\Desktop\\bb.doc");

        //获取文档1的最后一节
        Section lastsec = doc1.getLastSection();

        //遍历文档2的所有段落内容，添加到文档1
        for (Section section:(Iterable <Section>)doc2.getSections()) {
            for (DocumentObject obj:(Iterable <DocumentObject>)section.getBody().getChildObjects()) {
                lastsec.getBody().getChildObjects().add(obj.deepClone());
            }
        }

    // 保存合并后的文档
    doc1.saveToFile("C:\\Users\\Administrator\\Desktop\\aabb.doc", FileFormat.Docx_2010);
    }
}