package com.techconative.inmemory.pagination.modal;

import java.util.LinkedList;
import lombok.Getter;
import lombok.Setter;

/** PageResult class processed data per criteria provided */
@Getter
@Setter
public class PageResult<T> {

    /** This represents the total record count */
    private int totalCount;

    /** Array of custom object */
    private LinkedList<T> data;

    /** This specifies the number of records that need to be returned for a page. */
    private int limit;

    /** This specifies the page number */
    private int pageNumber;

    /** This specified the number of records matched with search text */
    private int filteredCount;
}
