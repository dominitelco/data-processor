package dataprocessor;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataModel {
    private String serviceNumber;
    private String billingType;
    private String usedApp;
    private Calendar initDate;
    private Calendar endDate;
    private BigDecimal kilobytesConsumed;

    public String getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(String serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    public String getBillingType() {
        return billingType;
    }

    public void setBillingType(String billingType) {
        this.billingType = billingType;
    }

    public String getUsedApp() {
        return usedApp;
    }

    public void setUsedApp(String usedApp) {
        this.usedApp = usedApp;
    }

    public Calendar getInitDate() {
        return initDate;
    }

    public void setInitDate(Calendar initDate) {
        this.initDate = initDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getKilobytesConsumed() {
        return kilobytesConsumed;
    }

    public void setKilobytesConsumed(BigDecimal kilobytesConsumed) {
        this.kilobytesConsumed = kilobytesConsumed;
    }

    public DataModel getFromStringLine(String line) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String[] lineValues = line.split(",");

        setServiceNumber(lineValues[0]);
        setBillingType(lineValues[1]);
        setUsedApp(lineValues[2]);

        Date date = sdf.parse(lineValues[3]);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        setInitDate(cal);

        date = sdf.parse(lineValues[4]);
        cal = Calendar.getInstance();
        cal.setTime(date);
        setEndDate(cal);

        setKilobytesConsumed(BigDecimal.valueOf(Long.parseLong(lineValues[5])));

        return this;
    }
}
