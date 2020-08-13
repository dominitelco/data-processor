package dataprocessor;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class CallsFileDecoratorTest {
    @Test
    public void with_empty_file_yields_empty()
    {
        CallsFileReader reader = mock(CallsFileReader.class);

        CallsFileDecorator decorator = new CallsFileDecorator(reader);

        assertTrue( decorator.decorateLines().size() == 0 );
    }

    @Test
    public void with_non_empty_file_yields_non_empty()
    {
        List<String> fakeLines = new ArrayList<String>();
        fakeLines.add("Name,Lastname");
        fakeLines.add("Peter,Parker");

        CallsFileReader reader = mock(CallsFileReader.class);
        when(reader.readAllLines()).thenReturn(fakeLines);

        CallsFileDecorator decorator = new CallsFileDecorator(reader);
        
        assertTrue( decorator.decorateLines().size() == 2 );
    }

    @Test
    public void read_file_line_into_data_model() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh24:mm:ss");

        String fakeLines = "18096886027,POSTPAGO,AMAZON_MUSIC,2020-06-31 10:49:27,2020-06-31 11:59:30,2560";
        String[] lineValues = fakeLines.split(",");

        DataModel model = new DataModel().getFromStringLine(fakeLines);

        Assert.assertEquals(model.getServiceNumber(), lineValues[0]);
        Assert.assertEquals(model.getBillingType(), lineValues[1]);
        Assert.assertEquals(model.getUsedApp(), lineValues[2]);


        Assert.assertEquals(model.getServiceNumber(), lineValues[0]);
        Assert.assertEquals(model.getBillingType(), lineValues[1]);
        Assert.assertEquals(model.getUsedApp(), lineValues[2]);
        Assert.assertEquals(model.getKilobytesConsumed(), BigDecimal.valueOf(Long.parseLong(lineValues[5])));
    }
}