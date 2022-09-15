package com.techconative.inmemory.pagination.modal;

import lombok.Data;

import java.util.List;

@Data
public class DateCounter {
    public String id;
    public List<DailyFields> dateCounter;
}
