package com.zhy.mytest;
 
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import cn.hutool.core.lang.Console;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfWriter;
 
/**
 * 第1章：PDF和iText简介
 *	1.1第一个iText示例：Hello World
 */
public class ITestDemo {
	 /** 生成的PDF文件的路径。 */
    public static final String RESULT = "D:/hello.pdf";
    /**
     * 生成的压缩包的路径
     */
    public static final String ZIP_RESULT = "D:/hello.zip";
 
    /**
     *   创建一个PDF文件：hello.pdf 
     * @param    args    no arguments needed
     */
    public static void main(String[] args) throws Exception {
        //创建一个简单的pdf文档
    	//new ITestDemo().createPdf(RESULT);
        //创建一个最大纸张大小的文档
        //new ITestDemo().createMaxPdf(RESULT);
        //创建一个横向的纸张大小的文档
        //new ITestDemo().createRotatePdf(RESULT);
        //new ITestDemo().createMirroredMargins(RESULT);
        new ITestDemo().createPdfZip(ZIP_RESULT);

    }
 
    /**
     *   创建PDF文档.
     * @param filename 新PDF文档的路径
     * @throws    DocumentException 
     * @throws    IOException 
     */
    public void createPdf(String filename) throws DocumentException, IOException {
        // 第一步 创建文档实例
        Document document = new Document();
        // 第二步 获取PdfWriter实例
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        // 第三步 打开文档
        document.open();
        // 第四步 添加段落内容
        document.add(new Paragraph("itext hello world!"));
        // 第五部 操作完成后必须执行文档关闭操作。
        document.close();
    }


    public void createMaxPdf(String filename) throws Exception {
        // 第一步
        // 设置为最大页面尺寸
        Document document = new Document(new Rectangle(14400, 14400));
        // 第二步 获取实例
        PdfWriter writer =PdfWriter.getInstance(document, new FileOutputStream(filename));
        // 改变用户单位 使用此方法设置用户单位。UserUnit是定义默认用户空间单位的值。最小UserUnit为1（1个单位= 1/72英寸）。最大UserUnit为75,000。请注意，此用户单元仅适用于从PDF1.6开始！
        writer.setUserunit(75000f);
        // step 3
        document.open();
        // step 4
        document.add(new Paragraph("Hello World"));
        // step 5
        document.close();
        Console.log("创建成功");
    }


    //创建宽的pdf
    public void createRotatePdf(String filename) throws Exception {
        // 第一步
        //横向的格式通过 rotate() 方法
        //Document document = new Document(PageSize.LETTER.rotate());
         // 除了通过rotate() 方法我们也可以指定 pdf文件的宽度大于高度来设置成横向显示。
        Document document = new Document(new Rectangle(792,400));
        // 第二步
        PdfWriter.getInstance(document, new FileOutputStream(RESULT));
        // 第三步
        document.open();
        // 第四步
        document.add(new Paragraph("Hello World 792 400"));
        // 第五步
        document.close();
        Console.log("创建成功");
    }


    //创建可以装订成册的pdf
    public void createMirroredMargins(String filename) throws Exception {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(RESULT));
        document.setPageSize(PageSize.A5);
        document.setMargins(36, 72, 108, 180);
        document.setMarginMirroring(true);
        // step 3
        document.open();
        // step 4
        document.add(new Paragraph(
                "The left margin of this odd page is 36pt (0.5 inch); " +
                        "the right margin 72pt (1 inch); " +
                        "the top margin 108pt (1.5 inch); " +
                        "the bottom margin 180pt (2.5 inch)."));
        Paragraph paragraph = new Paragraph();
        paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
        for (int i = 0; i < 20; i++) {
            paragraph.add("Hello World! Hello People! " +
                    "Hello Sky! Hello Sun! Hello Moon! Hello Stars!");
        }
        document.add(paragraph);
        document.add(new Paragraph(
                "The right margin of this even page is 36pt (0.5 inch); " +
                        "the left margin 72pt (1 inch)."));
        // step 5
        document.close();
        Console.log("创建成功");
    }


    // 压缩多个pdf文件
    public void createPdfZip(String filename) throws Exception {
        ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(filename));
        for(int i=1;i<=10;i++){
            ZipEntry zipEntry = new ZipEntry("hello" + i + ".pdf");
            zip.putNextEntry(zipEntry);
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, zip);
            writer.setCloseStream(false);
            document.open();
            document.add(new Paragraph("hello world zip " + i));
            document.close();
            //关闭每一个流
            zip.closeEntry();
        }
        //关闭流
        zip.close();
        Console.log("创建成功");
    }
}