package co.loyyee.milk;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class MilkRecords {

    private final MilkRecordsReader reader;
    private List<Milk> milkRecords = new ArrayList<>();

    public MilkRecords() {
        reader = new MilkRecordsReader();
    }


    public List<Milk> findAll() {
        if(milkRecords.isEmpty()) {
            milkRecords = reader.read();
        }
        // Collections.shuffle(milkRecords);
        return milkRecords.stream().limit(10).toList();
    }

}
