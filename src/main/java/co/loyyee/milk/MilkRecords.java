package co.loyyee.milk;

import java.util.ArrayList;
import java.util.List;

public class MilkRecords {

    private final MilkRecordsReader reader;
    private List<Milk> milkRecords = new ArrayList<>();

    public MilkRecords() {
        reader = new MilkRecordsReader();
    }

    public List<Milk> findAll() {
        if (milkRecords.isEmpty()) {
            milkRecords = reader.read();
        }
        return milkRecords.stream().limit(10).toList();
    }

}
