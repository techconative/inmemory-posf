package com.techconative.inmemory.pagination.modal;

import java.util.List;
import lombok.Data;

@Data
public class DateCounter {
    public String id;
    public List<DailyFields> dateCounter;
}
