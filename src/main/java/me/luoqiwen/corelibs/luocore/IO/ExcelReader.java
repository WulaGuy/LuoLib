package me.luoqiwen.corelibs.luocore.IO;

import me.luoqiwen.corelibs.luocore.IO.Exceptions.NotExcelFileException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ExcelReader
{
    public enum ExcelType
    {
        XLS, XLSX
    }

    private final Workbook wb;
    public ExcelReader(File Excel) throws IOException
    {
        String name = Excel.getName();
        if (name.endsWith(".xls"))
            wb = new HSSFWorkbook(new FileInputStream(Excel));
        else if (name.endsWith(".xlsx"))
            wb = new XSSFWorkbook(new FileInputStream(Excel));
        else
            throw new NotExcelFileException();
    }
    public ExcelReader(InputStream stream, ExcelType type) throws IOException
    {
        switch (type)
        {
            case XLS: wb = new HSSFWorkbook(stream);break;
            case XLSX: wb = new XSSFWorkbook(stream);break;
            default: throw new NotExcelFileException();
        }
    }



    public synchronized String getString(int sheet, int row, int cell)
    {
        try
        {
            return wb.getSheetAt(sheet-1).getRow(row-1).getCell(cell-1).toString();
        }
        catch (RuntimeException e)
        {
            return null;
        }
    }
    public int getInt(int sheet, int row, int cell)
    {
        String get = getString(sheet, row, cell);
        try
        {
            return get != null ? Integer.parseInt(get.substring(0, get.indexOf("."))) : 0;
        }
        catch (RuntimeException e)
        {
            return 0;
        }
    }
    public long getLong(int sheet, int row, int cell)
    {
        String get = getString(sheet, row, cell);
        return get != null ? Long.parseLong(get) : 0;
    }
    public float getFloat(int sheet, int row, int cell)
    {
        String get = getString(sheet, row, cell);
        return get != null ? Float.parseFloat(get) : 0;
    }
    public double getDouble(int sheet, int row, int cell)
    {
        String get = getString(sheet, row, cell);
        return get != null ? Double.parseDouble(get) : 0;
    }

    public synchronized String getSheetName(int sheet)
    {
        return wb.getSheetName(sheet-1);
    }

    public synchronized void createSheet(String name)
    {
        wb.createSheet(name);
    }
    public synchronized Sheet getSheet(int index)
    {
        return wb.getSheetAt(index-1);
    }
    public synchronized Row getRow(int sheet, int row)
    {
        return wb.getSheetAt(sheet-1).getRow(row-1);
    }
    public synchronized Cell getCell(int sheet, int row, int cell)
    {
        return wb.getSheetAt(sheet-1).getRow(row-1).getCell(cell-1);
    }

}