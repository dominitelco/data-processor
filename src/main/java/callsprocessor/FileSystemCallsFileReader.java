package dataprocessor;

import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileSystemCallsFileReader implements CallsFileReader {
    private final String fullPath;

    public FileSystemCallsFileReader(final String fullPath) {
        this.fullPath = fullPath;
    }

    @Override
    public List<String> readAllLines() {
        try {
            Path thePath = Paths.get(fullPath);
            return Files.readAllLines(thePath, Charset.defaultCharset());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<String>();
    }
}