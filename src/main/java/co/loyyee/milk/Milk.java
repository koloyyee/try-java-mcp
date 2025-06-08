package co.loyyee.milk;

import java.time.LocalDate;

public record Milk(String sample, String type,
        LocalDate startDate, LocalDate endDate,
        String station, String province,
        Double sr90Activity, Double sr90Error, Double sr90Calcium
        ) {

}