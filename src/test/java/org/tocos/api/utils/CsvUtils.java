package org.tocos.api.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class CsvUtils {

    public static Stream<CSVRecord> parseCsv(String filePath) throws IOException {
        CSVFormat format = CSVFormat.DEFAULT.builder()
                                            .setHeader()
                                            .setSkipHeaderRecord(true)
                                            .build();

        try (var reader = Files.newBufferedReader(Paths.get(filePath));
             var parser = new CSVParser(reader, format)) {
            return parser.getRecords().stream();
        }
    }
}
