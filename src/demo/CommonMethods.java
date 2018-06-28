/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Vinoth
 */
public class CommonMethods {

    String uploadPath = "";
    String downloadPath = "";

    public void createPdf()
            throws IOException, DocumentException {

        String fileName = "Invoice.pdf";
        final String dirName
                = System.getProperty("user.dir") + File.separator + "Download";
        File file = new File(dirName);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
        
        String filename = dirName + File.separator+fileName;
        
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        // step 3
        document.open();
        // step 4
        document.add(createFirstTable());
        // step 5
        document.close();
    }

    public PdfPTable createFirstTable() throws FileNotFoundException, IOException {
        // a table with three columns
        PdfPTable table = new PdfPTable(3);

        String excelFilePath = uploadPath;
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();

        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        System.out.print(cell.getStringCellValue());
                        table.addCell(cell.getStringCellValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print(cell.getNumericCellValue());
                        table.addCell(Double.toString(cell.getNumericCellValue()));
                        break;
                }
                System.out.print(" - ");
            }
            System.out.println();

        }
        return table;
    }

    public void readProperties() {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("config.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            uploadPath = prop.getProperty("UploadPath");
            downloadPath = prop.getProperty("DownloadPath");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void genLog(String message) {
        Logger logger = Logger.getLogger("MyLog");
        FileHandler fh;

        try {
            String logFolderName = "Logs";
            String logFileName = "MyLogFile.log";
            String path = System.getProperty("user.dir") + File.separator + logFolderName;
            File file = new File(path);
            if (!file.exists()) {
                if (file.mkdir()) {
                    System.out.println("Directory is created!");
                } else {
                    System.out.println("Failed to create directory!");
                }
            }
            String path1 = path + File.separator + logFileName;
            fh = new FileHandler(path1);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
//            java.util.logging.FileHandler.limit = 1024000;
            logger.setUseParentHandlers(false);
            logger.info(message);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//
//    public void readExcel() throws BiffException, IOException {
//        String FilePath = uploadPath;
//        FileInputStream fs = new FileInputStream(FilePath);
//        Workbook wb = Workbook.getWorkbook(fs);
//
//        // TO get the access to the sheet
//        Sheet sh = wb.getSheet("Sheet1");
//
//        // To get the number of rows present in sheet
//        int totalNoOfRows = sh.getRows();
//
//        // To get the number of columns present in sheet
//        int totalNoOfCols = sh.getColumns();
//
//        for (int row = 0; row < totalNoOfRows; row++) {
//
//            for (int col = 0; col < totalNoOfCols; col++) {
//                System.out.print(sh.getCell(col, row).getContents() + "\t");
//            }
//            System.out.println();
//        }
//    }
}
