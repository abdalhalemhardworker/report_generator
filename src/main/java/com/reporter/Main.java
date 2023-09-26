package com.reporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.Reports.LoatReport;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Workbook;

public class Main {
    public static void main(String[] args) throws Exception {
        LoatReport loatReport = new LoatReport(2480, 3508);
        loatReport.render();
        loatReport.save("test.jpg");
    }

    public static void notmain(String[] args) throws Exception {
        String fileLocation = "C:\\Users\\C.m™ Lap\\DEV\\demo\\src\\main\\resources\\AlSafua.xlsx";
        File debugFile = new File("debug.txt");
        FileWriter debugFileWriter = new FileWriter(debugFile);

        FileInputStream file = new FileInputStream(new File(fileLocation));
        Workbook workBook = new XSSFWorkbook(file);
        XSSFSheet workSheet = (XSSFSheet) workBook.getSheetAt(workBook.getSheetIndex("بيان اللوط"));
        List<XSSFTable> tables = workSheet.getTables();
        XSSFTable loat_table = null;

        for (XSSFTable table : tables) {
            if (table.getName().equals("بيان_اللوط")) {
                loat_table = table;
            }
        }

        if (loat_table == null) {
            System.out.println("table not found");
            workBook.close();
            debugFileWriter.close();
            return;
        }

        int startRow = loat_table.getStartRowIndex();
        int endRow = loat_table.getEndRowIndex();
        int startColumn = loat_table.getStartColIndex();
        int endColumn = loat_table.getEndColIndex();

        String loatNumber = "251";

        for (int row = startRow; row <= endRow; row++) {
            XSSFRow sheetRow = workSheet.getRow(row);
            XSSFCell sheetCell = sheetRow.getCell(startColumn);

            DataFormat format = workBook.createDataFormat();
            CellStyle style = workBook.createCellStyle();

            style.setDataFormat(format.getFormat("0"));

            sheetCell.setCellStyle(style);

            if (sheetCell.getRawValue().equals(loatNumber)) {
                for (int col = startColumn; col <= endColumn; col++) {
                    sheetCell = sheetRow.getCell(col);
                    debugFileWriter.append(sheetCell.getRawValue());
                    if (col != endColumn)
                        debugFileWriter.write(", ");
                }
                debugFileWriter.write('\n');
            }

        }

        debugFileWriter.close();
        workBook.close();
    }
}