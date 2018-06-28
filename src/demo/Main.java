package demo;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Main {

    

    public static void main(String[] args) throws IOException, DocumentException {
        CommonMethods cm = new CommonMethods();
//        cm.genLog("*********************************************");
//        cm.genLog("*******************STARTED*******************");
//        cm.genLog("*********************************************");
        System.out.println("*********************************************");
        System.out.println("*******************STARTED*******************");
        System.out.println("*********************************************");
        cm.readProperties();
        
        cm.createPdf();
//        cm.genLog("*********************************************");
//        cm.genLog("*******************ENDED*******************");
//        cm.genLog("*********************************************");
        System.out.println("*********************************************");
        System.out.println("*******************ENDED*********************");
        System.out.println("*********************************************");
    }

}
