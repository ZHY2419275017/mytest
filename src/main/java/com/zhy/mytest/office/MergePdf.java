package com.zhy.mytest.office;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfDocumentBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author: zhy
 * @Date:2020/11/28
 * @Description:
 **/
public class MergePdf {

    public static void main(String[] args) throws Exception {

        String outputFile = "abc.pdf";
        FileInputStream stream1 = new FileInputStream(new File("a.pdf"));
        FileInputStream stream2 = new FileInputStream(new File("b.pdf"));
        FileInputStream stream3 = new FileInputStream(new File("c.pdf"));
        //加载PDF示例文档
        InputStream[] streams = new FileInputStream[]{stream1, stream2, stream3};

        //合并PDF文档
        PdfDocumentBase doc = PdfDocument.mergeFiles(streams);

        //保存文档
        doc.save(outputFile);
        doc.close();
    }
}
