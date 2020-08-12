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
}