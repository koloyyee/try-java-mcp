package co.loyyee.milk;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.swing.text.DateFormatter;

import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRecord;

/**
 * Read from resources directory and return as an array of milk records
 */
public class MilkRecordsReader {

    public static void main(String[] args) {
        var m = new MilkRecordsReader();
        var milks = m.read();
        System.out.println(milks);

    }

    List<Milk> read() {
        try (var inputStream = getClass().getClassLoader().getResourceAsStream("nms_strontium90_milk_ssn_strontium90_lait.csv"); CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(inputStream);) {

            return csv.stream()
                    .skip(1)
                    .map(CsvRecord::getFields)
                    .map(rowToMilk)
                    .toList();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return List.of();
    }

    private Double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NullPointerException e) {
            return null;
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    private LocalDate parseDate(String value) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yy");
        if (value.toLowerCase().contains("sep")) {
            var corrected = value.replace("Sep", "Sept");
            return LocalDate.parse(corrected, formatter);
        } else {
            return LocalDate.parse(value, formatter);
        }
    }

    private Function<List<String>, Milk> rowToMilk = (row) -> {
        var sr90Activity = parseDouble(row.get(6));
        var sr90Error = parseDouble(row.get(7));
        var sr90Calcium = parseDouble(row.get(8));

        var startDate = parseDate(row.get(2));
        var endDate = parseDate(row.get(3));

        return new Milk(
                row.get(0),
                row.get(1),
                startDate,
                endDate,
                row.get(4),
                row.get(5),
                sr90Activity,
                sr90Error,
                sr90Calcium
        );
    };
}
