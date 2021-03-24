package com.zhy.mytest.office;

import com.spire.barcode.*;
import com.spire.pdf.*;
import com.spire.pdf.barcode.*;
import com.spire.pdf.graphics.*;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;


public class AddBarcodeInPDF {
    public static void main(String[] args) {
    // 创建PdfDocument对象
    PdfDocument pdf = new PdfDocument("C:\\Users\\Administrator\\Desktop\\bb.pdf");

        //添加一页
        PdfPageBase page = pdf.getPages().add();

        //初始化y变量
        double y = 15;

        //创建字体
        PdfFont font= new PdfFont(PdfFontFamily.Helvetica, 12,PdfFontStyle.Bold);

        // 绘制文本“Codebar:”到PDF,并绘制Codebar条码到PDF
        PdfTextWidget text = new PdfTextWidget();
        text.setFont(font);
        text.setText("Codebar:");
        PdfLayoutResult result = text.draw(page, 0, y);
        y =(float)(result.getBounds().getY()+ result.getBounds().getHeight() + 2);
        PdfCodabarBarcode codebar= new PdfCodabarBarcode("00:12-3456/7890");//创建条码
        codebar.setBarcodeToTextGapHeight(1f);
        codebar.setBarHeight(25f);
        codebar.setEnableCheckDigit(true);
        codebar.setShowCheckDigit(true);
        codebar.setTextDisplayLocation(TextLocation.Bottom);
        PdfRGBColor blue = new PdfRGBColor(Color.blue);
        codebar.setTextColor(blue);
        Point2D.Float point = new Point2D.Float();
        point.setLocation(0,y);
        codebar.draw(page,point);//绘制条码到PDF页面
//        y = codebar.getBounds().getY()+ codebar.getBounds().getHeight() + 5;
//
//        //绘制文本“Code128-A:”到PDF，并绘制Code128A条码到PDF
//        text.setText("Code128-A:");
//        result = text.draw(page, 0, y);
//        page = result.getPage();
//        y =result.getBounds().getY()+ result.getBounds().getHeight() + 2;
//        PdfCode128ABarcode code128 = new PdfCode128ABarcode("HELLO 00-123");
//        code128.setBarcodeToTextGapHeight(1f);
//        code128.setBarHeight(25f);
//        code128.setTextDisplayLocation(TextLocation.Bottom);
//        code128.setTextColor(blue);
//        point.setLocation(point.x,y);
//        code128.draw(page, point);
//        y =code128.getBounds().getY()+ code128.getBounds().getHeight() + 5;
//
//        //绘制文本“Code39”到PDF，绘制Code39条形码到PDF
//        text.setText("Code39:");
//        result = text.draw(page, 0, y);
//        page = result.getPage();
//        y =result.getBounds().getY()+ result.getBounds().getHeight() + 2;
//        PdfCode39Barcode code39 = new PdfCode39Barcode("16-273849");//绘制条码
//        code39.setBarcodeToTextGapHeight(1f);
//        code39.setBarHeight(25f);
//        code39.setTextDisplayLocation(TextLocation.Bottom);
//        code39.setTextColor(blue);
//        point.setLocation(point.x,y);
//        code39.draw(page, point);//绘制条码到PDF页面

//        //生成二维码图片，绘制到PDF页面
//        text.setText("QRCode:");//绘制文本“QR Code:”到PDF
//        result = text.draw(page, 200, 0);
//        page = result.getPage();
//        BarcodeSettings settings = new BarcodeSettings();//创建二维码图形
//        settings.setType(BarCodeType.QR_Code);
//        settings.setData("123456789");
//        settings.setData2D("123456789");
//        settings.setX(1f);
//        settings.setLeftMargin(0);
//        settings.setShowTextOnBottom(true);
//        settings.setQRCodeECL(QRCodeECL.Q);
//        settings.setQRCodeDataMode(QRCodeDataMode.Numeric);
//        BarCodeGenerator generator = new BarCodeGenerator(settings);
//        Image image = generator.generateImage();
//        PdfImage pdfImage = PdfImage.fromImage((BufferedImage)image);//绘制二维码图片到PDF
//        y = result.getBounds().getY()+ result.getBounds().getHeight() + 2;
//        page.getCanvas().drawImage(pdfImage,200,y);

       // 保存PDF文档
        pdf.saveToFile("C:\\Users\\Administrator\\Desktop\\添加条码、二维码.pdf");
        pdf.dispose();
    }
}