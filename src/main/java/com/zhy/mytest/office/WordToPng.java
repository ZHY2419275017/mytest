package com.zhy.mytest.office;

import com.spire.doc.*;
import com.spire.doc.documents.ImageType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WordToPng {
    public static void main(String[]args) throws IOException {
        // 加载测试文档
        Document doc = new Document("C:\\Users\\Administrator\\Desktop\\科技示范工程.doc");

        //将文档指定页保存为Png格式的图片
        BufferedImage image = doc.saveToImages( 0, ImageType.Bitmap);
        File file = new File("C:\\Users\\Administrator\\Desktop\\ToPNG.png");
        ImageIO.write(image, "PNG", file);

//        // 将Word转为PDF
//        doc.saveToFile("C:\\Users\\Administrator\\Desktop\\Word转PDF.pdf", FileFormat.PDF);
//
//    // 将Word保存为SVG格式
//    doc.saveToFile("C:\\Users\\Administrator\\Desktop\\ToSVG.svg", FileFormat.SVG);
//
//    // 将Word保存为RTF格式
//    doc.saveToFile("C:\\Users\\Administrator\\Desktop\\ToRTF.rtf", FileFormat.Rtf);
//
//    // 将Word保存为XPS格式
//    doc.saveToFile("C:\\Users\\Administrator\\Desktop\\ToXPS.xps", FileFormat.XPS);
//
//    // 将Word保存为XML格式
//    doc.saveToFile("C:\\Users\\Administrator\\Desktop\\ToXML.xml", FileFormat.Xml);
//
//    // 将Word保存为TXT格式
//    doc.saveToFile("C:\\Users\\Administrator\\Desktop\\ToTXT.txt", FileFormat.Txt);
    }
}