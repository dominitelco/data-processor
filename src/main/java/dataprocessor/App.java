package dataprocessor;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Calls processor...
 */
public class App 
{

    public static List<DataModel> userDataRecords;

    public static void main(final String[] args) throws ParseException {
        if (args.length == 0) {
            System.err.println("No arguments received, at least one is require with the full path of the file to analyze.");
            return;
        }

        readFileAsList(args[0]);
    }

    private static void readFileAsList(final String fileFullPath) throws ParseException {
        CallsFileReader reader = new FileSystemCallsFileReader(fileFullPath);
        CallsFileDecorator decorator = new CallsFileDecorator(reader);
        userDataRecords = new ArrayList<>();
        Long totalMesActual = 0L;
//        for (String line : decorator.decorateLines()) {
//            DataModel model = new DataModel().getFromStringLine(line);
//            if (model.getInitDate().getTimeInMillis() >= Calendar.getInstance().getTimeInMillis() && model.getEndDate().getTimeInMillis() <= Calendar.getInstance().getTimeInMillis()) {
//                totalMesActual += model.getKilobytesConsumed().longValue() * 5L;
//            }
//            userDataRecords.add(model);
//        }
        for (int i = 1 ; i < decorator.decorateLines().size(); i++) {
            String line = decorator.decorateLines().get(i);
            DataModel model = new DataModel().getFromStringLine(line);
            if (isInCurrentMonth(model)) {
                totalMesActual += model.getKilobytesConsumed().longValue() * 5L;
            }
            userDataRecords.add(model);
        }
        System.out.print("\n---------- FACTURA MES ACTUAL ---------\n");
        System.out.print(String.format("Total a pagar: %s", totalMesActual));
        System.out.print("\n\n\n---------- FIN FACTURA MES ACTUAL ---------\n\n\n");
    }

    private static boolean isInCurrentMonth(DataModel model) {
        LocalDate date =LocalDate.now();
        Date initDate = model.getInitDate().getTime();
        Date endDate = model.getEndDate().getTime();
        return true;
    }

}
