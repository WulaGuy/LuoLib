package me.luoqiwen.corelibs.luocore.IO;

import me.luoqiwen.corelibs.luocore.IO.Exceptions.NotYamlFileException;
import org.yaml.snakeyaml.Yaml;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class YamlFile
{
    private final Yaml yaml = new Yaml();
    private final HashMap<Object, Object> map;
    private final List list;

    public YamlFile(File YamlFile) throws IOException
    {
        if (YamlFile.getName().endsWith(".yml"))
        {
            FileInputStream stream = new FileInputStream(YamlFile);
            map = yaml.load(stream);
            list = yaml.load(stream);
        }
        else
            throw new NotYamlFileException();
    }

    public YamlFile(InputStream stream) throws IOException
    {
        map = yaml.load(stream);
        list = yaml.loadAs(stream, List.class);
    }

    public HashMap<Object, Object> getMap()
    {
        return map;
    }

    public Object get(Object Index)
    {
        String separator = ".";
        if (Index instanceof String)
        {
            String index = (String) Index;
            if (!index.contains(separator))
                return map.get(index);
            else
            {
                String[] indexes = index.split("\\.");
                HashMap<String, Object> last = (HashMap<String, Object>) map.clone();
                for (int i = 0; i < indexes.length-1; i++)
                {
                    if (last == null)
                        break;
                    else
                        last = (HashMap<String, Object>) last.get(indexes[i]);
                }
                return last.getOrDefault(indexes[indexes.length-1], null);
            }
        }
        else
            return map.get(Index);
    }

    public String getString(Object index)
    {
        return String.valueOf(get(index));
    }

    public int getInt(Object index)
    {
        return (int) get(index);
    }

    public long getLong(Object index)
    {
        return Long.parseLong(getString(index));
    }

    public double getDouble(Object index)
    {
        return (double) get(index);
    }

    public float getFloat(Object index)
    {
        return Float.parseFloat(getString(index));
    }

    public boolean getBoolean(Object index)
    {
        return (boolean) get(index);
    }

    public List<String> getStringList(Object index)
    {
        return (List<String>) get(index);
    }

    public List<String> getList()
    {
        return (List<String>) list;
    }


    @Override
    public String toString()
    {
        return yaml.toString();
    }

    public Yaml getYaml()
    {
        return yaml;
    }
}
