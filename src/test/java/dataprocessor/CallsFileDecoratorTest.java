package dataprocessor;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
    public void read_file_line_into_data_model() {
        String fakeLines = "18096886027,POSTPAGO,AMAZON_MUSIC,2020-06-31 10:49:27,2020-06-31 11:59:30,2560";
        DataModel model = convertFileToDataModel(fakeLines);
        Assert.assertEquals(model.getServiceNumber(), fakeLines.split(",").get(0));
        Assert.assertEquals(model.getBillingType(), fakeLines.split(",").get(1));
        Assert.assertEquals(model.getUsedApp(), fakeLines.split(",").get(2));
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date date = sdf.parse(CalendarfakeLines.split(",").get(4));
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        Assert.assertEquals(model.getInitDate(), fakeLines.split(",").get(3));

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        date = sdf.parse(CalendarfakeLines.split(",").get(4));
        cal = Calendar.getInstance();
        cal.setTime(date);
        
        Assert.assertEquals(model.getEndDate().toString(), cal.toString());
        Assert.assertEquals(model.getKilobytesConsumed(), fakeLines.split(",").get(5));
    }
}