package me.luoqiwen.corelibs.luocore.IO.Exceptions;

import java.io.IOException;

public class NotExcelFileException extends IOException
{
    private static final String msg = "Not an Excel File!";
    public NotExcelFileException()
    {
        super(msg);
    }
    @Override
    public String toString()
    {
        return msg;
    }
}
