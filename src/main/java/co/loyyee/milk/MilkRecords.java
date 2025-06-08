package co.loyyee.milk;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class MilkRecords {

    private final MilkRecordsReader reader;
    private List<Milk> milkRecords = new ArrayList<>();

    public MilkRecords() {
        reader = new MilkRecordsReader();
    }

    @Tool(name = "get_all_records", description = "just the top 10 for demo")
    public List<Milk> findAll() {
        return milkRecords.stream().limit(10).toList();
    }

    @Tool(name = "get_by_station", description = "find first record get by station name")
    public Milk find(String station) {
        return milkRecords.stream().filter(r -> r.station().equals(station)).findFirst().orElse(null);
    }

    @PostConstruct
    public void init() {
        milkRecords = reader.read();
    }
}
