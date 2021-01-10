package me.luoqiwen.corelibs.luocore.IO.Exceptions;

import java.io.IOException;

public class NotYamlFileException extends IOException
{
    private static final String msg = "Not an Yaml File!";
    public NotYamlFileException()
    {
        super(msg);
    }
    @Override
    public String toString()
    {
        return msg;
    }
}
