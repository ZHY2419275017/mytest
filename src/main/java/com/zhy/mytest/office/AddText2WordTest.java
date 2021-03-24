package com.zhy.mytest.office;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.documents.BookmarksNavigator;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.documents.TextWrappingStyle;
import com.spire.doc.fields.DocPicture;

/**
 * @author: zhy
 * @Date:2021/2/6
 * @Description:  给word文档添加文本文字
 **/
public class AddText2WordTest {

      public static void main(String[] args) {
          Document doc = new Document();
          doc.loadFromFile("C:\\\\Users\\\\Administrator\\\\Desktop\\\\123.doc");
          //定位到指定书签位置起始标签位置，插入图片
          BookmarksNavigator bookmarksNavigator1 = new BookmarksNavigator(doc);
          bookmarksNavigator1.moveToBookmark("projectName",true,false);
          bookmarksNavigator1.insertText("测试项目申报标题");
          bookmarksNavigator1.moveToBookmark("applyUnit",true,false);
          bookmarksNavigator1.insertText("申报单位测试");
          bookmarksNavigator1.moveToBookmark("dept",true,false);
          bookmarksNavigator1.insertText("主管部门");
//          Paragraph para = new Paragraph(doc);
//          DocPicture picture = para.appendPicture("eth.png");
//          picture.setTextWrappingStyle(TextWrappingStyle.Through);
//          bookmarksNavigator1.insertParagraph(para);
          //定位到指定书签位置末尾标签位置，插入文本
//          BookmarksNavigator bookmarksNavigator2 = new BookmarksNavigator(doc);
//          bookmarksNavigator2.moveToBookmark("bookmark1",false,true);
//          bookmarksNavigator2.insertText("新插入的文本!!!");
          //保存文档
          doc.saveToFile("C:\\\\Users\\\\Administrator\\\\Desktop\\\\result.pdf",  FileFormat.PDF);
          doc.dispose();
      }
}
