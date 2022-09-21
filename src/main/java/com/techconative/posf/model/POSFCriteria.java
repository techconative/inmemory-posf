package com.techconative.posf.model;

import lombok.Getter;
import lombok.Setter;

/** POSFCriteria class contains page, sort, filter and search constraints */
@Getter
@Setter
public class POSFCriteria {

    /**
     * Number of items that need to to displayed in a page If it is 0 then it will return all
     * records
     */
    private int limit;

    /** Page number */
    private int pageNumber;

    /** Column's ordering criteria. */
    private OrderingCriteria sort;

    /** Name of the column to sort */
    private String column;

    /** Filter the value on particular column Eg : name~krishnan */
    private String filter;
}
