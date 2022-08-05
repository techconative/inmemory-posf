package com.techconative.inmemory.pagination.modal;

import java.util.LinkedList;


public class PageResult<T> {

    /**
     * This represents the total record count
     */
    private int totalCount;

    /**
     * Array of custom object
     */
    private LinkedList<T> data;

    /**
     * This specifies the number of records that need to be returned for a page.
     */
    private int limit;

    /**
     * This specifies the page number
     */
    private int pageNumber;

    /**
     * This specified the number of records matched with search text
     */
    private int filteredCount;


    public int getTotalCount() {

        return totalCount;
    }


    public void setTotalCount(int totalCount) {

        this.totalCount = totalCount;
    }


    public LinkedList<T> getData() {

        return data;
    }


    public void setData(LinkedList<T> data) {

        this.data = data;
    }


    public int getLimit() {

        return limit;
    }


    public void setLimit(int limit) {

        this.limit = limit;
    }


    public int getPageNumber() {

        return pageNumber;
    }


    public void setPageNumber(int pageNumber) {

        this.pageNumber = pageNumber;
    }


    public int getFilteredCount() {

        return filteredCount;
    }


    public void setFilteredCount(int filteredCount) {

        this.filteredCount = filteredCount;
    }
}
