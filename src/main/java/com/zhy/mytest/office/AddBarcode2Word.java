package com.zhy.mytest.office;

import com.spire.barcode.*;
import com.spire.doc.*;
import com.spire.doc.documents.HorizontalAlignment;
import com.spire.doc.documents.Paragraph;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AddBarcode2Word {
    public static void main(String[] args) throws IOException {


        final String code = "JR202000001";

        //创建Document对象，加载Word文档
        Document doc = new Document();
        doc.loadFromFile("C:\\Users\\Administrator\\Desktop\\bb.doc");

        //获取所有section
        for (int i = 0 ; i<doc.getSections().getCount();i++) {
            Section section = doc.getSections().get(i);
            //使用Spire.Barcode的BarcodeSettings和BarcodeGenerator类创建条码并保存为图片
            BarcodeSettings settings = new BarcodeSettings();
            settings.setType(BarCodeType.Code_39);
            settings.setData(code);
            settings.setData2D(code);
            settings.setShowText(true);
            //条形码的高度
            settings.setBarHeight(8);
            settings.setX(0.3f);
            settings.setAutoResize(true);
            settings.setShowTextOnBottom(true);
            //如果为true则需要打开下面的设置
            settings.hasBorder(false);
//            settings.setBorderWidth(0.5f);
//            settings.setBorderColor(new Color(135,206,250));
//            settings.setBackColor(new Color(240,255,255));
            BarCodeGenerator barCodeGenerator = new BarCodeGenerator(settings);
            BufferedImage bufferedImage = barCodeGenerator.generateImage();
            ImageIO.write(bufferedImage, "png", new File(code+".png"));

//            //添加条码到正文段落
//            Paragraph paragraph = section.addParagraph();
//            //paragraph.setText("校验码：");
//            paragraph.appendPicture(code+".png");
//            paragraph.getFormat().setHorizontalAlignment(HorizontalAlignment.Right);

            //添加条码图片到Word页脚
            HeaderFooter footer = section.getHeadersFooters().getFooter();
            Paragraph footerpara = footer.addParagraph();
            //footerpara.setText("校验码：");
            footerpara.appendPicture(code+".png");
            footerpara.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
        }

        //保存文档
        doc.saveToFile("bb.doc", FileFormat.Docx_2013);
        doc.dispose();
    }
}