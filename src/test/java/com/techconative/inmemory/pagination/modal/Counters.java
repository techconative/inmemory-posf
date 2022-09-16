package com.techconative.inmemory.pagination.modal;

import java.util.List;
import lombok.Data;

@Data
public class Counters {

    public int id;
    public String title;
    public String description;
    public List<YearMonth> counters;
    public String msg;
}
