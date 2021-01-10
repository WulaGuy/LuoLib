import me.luoqiwen.corelibs.luocore.IO.YamlFile;

import java.io.IOException;

public class Test
{
    public static void main(String[] args)
    {
        YamlFile file;
        try
        {
            file = new YamlFile(ClassLoader.getSystemResourceAsStream("test.yml"));
        } catch (IOException e)
        {
            e.printStackTrace();
            return;
        }
        System.out.println(file.getLong("owner"));
        System.out.println(file);
    }
}
