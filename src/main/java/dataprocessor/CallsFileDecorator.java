package dataprocessor;

import java.util.ArrayList;
import java.util.List;

public class CallsFileDecorator {
    private final CallsFileReader reader;

    public CallsFileDecorator(final CallsFileReader reader) {
        this.reader = reader;
    }

    public List<String> decorateLines() {
        List<String> lines = reader.readAllLines();

        List<String> result = new ArrayList<String>(lines.size());
        int i = 0;
        for (String line : lines) {
            result.add(indexToPrefix(i++) + line);
        }

        return result;
    }

    private static String indexToPrefix(final int index) {
        return index < 1 ? "" : String.valueOf(index) + ": ";
    }
}