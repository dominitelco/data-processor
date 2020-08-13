package dataprocessor;

import java.util.ArrayList;
import java.util.List;

/**
 * Calls processor...
 */
public class App 
{

    public static List<DataModel> userDataRecords;

    public static void main(final String[] args)
    {
        if (args.length == 0) {
            System.err.println("No arguments received, at least one is require with the full path of the file to analyze.");
            return;
        }

        readFileAsList(args[0]);
    }

    private static void readFileAsList(final String fileFullPath) {
        CallsFileReader reader = new FileSystemCallsFileReader(fileFullPath);
        CallsFileDecorator decorator = new CallsFileDecorator(reader);
        userDataRecords = new ArrayList<>();
        for (String line : decorator.decorateLines()) {
            System.out.println(line);

        }
    }

//    -- Mes actual ():
//    Monto Total: RD xxxx.xx
//    Detalle:
//      Servicio #1: RD xxxx.xx
//      ...
//
//
//    -- Mes pasado ():
}
