package com.techconative.inmemory.pagination.modal;

import lombok.Data;

import java.util.List;

@Data
public class Counters {
    
    public int id;
    public String title;
    public String description;
    public List<YearMonth> counters;
    public String msg;

}
