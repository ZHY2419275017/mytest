package com.zhy.mytest.office;

import cn.hutool.core.lang.Console;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Table;
import com.spire.doc.TableRow;
import com.spire.doc.documents.*;
import com.spire.doc.fields.TextRange;
import com.spire.pdf.PdfDocument;

/** @author: zhy @Date:2020/11/26 @Description: */
public class replaceBookMark {

//      //使用文本替换书签
//      public static void main(String[] args) {
//        // 加载包含书签的Word文档
//        Document doc = new Document();
//        doc.loadFromFile("C:\\Users\\Administrator\\Desktop\\BarCodeToWord.docx");
//
//        // 定位到指定书签位置
//        BookmarksNavigator bookmarksNavigator = new BookmarksNavigator(doc);
//        bookmarksNavigator.moveToBookmark("bookmark");
//
//        // 用文本内容替换原有书签位置的文本，新替换的内容与原文格式一致
//        bookmarksNavigator.replaceBookmarkContent("地市端审核审核通过", true);
//
//        // 保存文档
//        doc.saveToFile("通过书签替换文本.docx", FileFormat.Docx_2013);
//        doc.dispose();
//        Console.log("替换成功");
//      }


    //使用表格替换书签
    public static void main(String[]args){
        //加载包含书签的Word文档
        Document doc = new Document();
        doc.loadFromFile("C:\\\\Users\\\\Administrator\\\\Desktop\\\\BarCodeToWord.docx");
        //声明数组内容
        String[][] data =
                {
                        new String[]{"分类", "等级", "编号"},
                        new String[]{"A", "一级", "01A"},
                        new String[]{"B", "二级", "02B"},
                        new String[]{"C", "三级", "03C"},
                };
        //创建表格
        Table table = new Table(doc, true);
        table.resetCells(4, 3);
        for (int i = 0; i < data.length; i++) {
            TableRow dataRow = table.getRows().get(i);
            for (int j = 0; j < data[i].length; j++) {
                TextRange range = dataRow.getCells().get(j).addParagraph().appendText(data[i][j]);
                range.getOwnerParagraph().getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
                range.getCharacterFormat().setFontName("楷体");
                dataRow.getRowFormat().setHorizontalAlignment(RowAlignment.Center);
                dataRow.getCells().get(j).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
            }
        }
        //创建TextBodyPart对象
        TextBodyPart bodyPart= new TextBodyPart(doc);
        bodyPart.getBodyItems().add(table);
        //定位到指定书签位置
        BookmarksNavigator bookmarkNavigator = new BookmarksNavigator(doc);
        bookmarkNavigator.moveToBookmark("bookmark");
        //使用表格替换原书签的内容
        bookmarkNavigator.replaceBookmarkContent(bodyPart);
        //保存文档
        doc.saveToFile("替换表格.docx", FileFormat.Docx);
        doc.dispose();

    }
}
