package com.zhy.mytest.office;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * word转pdf，系统中需要安装LibreOffice，仅支持windows/linux
 *
 */
public class pdfTest {
    /**
     * 判断是否是windows操作系统
     * @return
     */
    public static boolean isWindows() {
        String osInfo = System.getProperty("os.name").toLowerCase();
        return osInfo.indexOf("windows") >= 0;
    }

    /**
     * word转pdf
     * @param docPath
     * @return
     */
    public static String wordToPdf(String docPath) {
        String libreOfficePath = "C:\\Program Files\\LibreOffice\\program\\";

        if (!libreOfficePath.endsWith(File.separator))
            libreOfficePath += File.separator;
        //soffice --convert-to pdf  -outdir E:/test.docx
        String command = "";        // 执行转换字符串
        if (isWindows()) {
            command = libreOfficePath + "soffice --convert-to pdf  -outdir " + new File(docPath).getParent() + " " + docPath;
        } else {
            command = libreOfficePath + "soffice --invisible --convert-to pdf:writer_pdf_Export --outdir "
                    + new File(docPath).getParent() + " " + docPath;
        }
        execCommand(command);
        docPath = docPath.replace(".docx", ".pdf");
        docPath = docPath.replace(".doc", ".pdf");
        return docPath;
    }

    /**
     * 执行命令行
     * @param cmd
     * @return
     */
    public static boolean execCommand(String cmd) {
        Process process = null;
        BufferedReader br = null;
        try {
            process = Runtime.getRuntime().exec(cmd);
            br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            return false;
        } finally {
            try {
                process.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static void main(String[] args) {
    String pdfpath = wordToPdf("C:\\Users\\Administrator\\Desktop\\aa.doc");
        System.out.println(pdfpath);
    }


}
