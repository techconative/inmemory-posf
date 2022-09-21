package com.techconative.posf.model;

import java.util.List;
import lombok.Data;

@Data
public class DateCounter {
    public String id;
    public List<DailyFields> dateCounter;
}
