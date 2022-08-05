package com.techconative.inmemory.pagination.modal;


public class PaginationCriteria {

    /**
     * Number of items that need to to displayed in a page
     * If it is 0 then it will return all records
     */
    private int limit;

    /**
     * Page number
     */
    private int pageNumber;

    /**
     * search text that will do searching in all columns
     */
    private String query;

    /**
     * Column's ordering criteria.
     */
    private OrderingCriteria sort;

    /**
     * Name of the column to sort
     */
    private String column;

    /**
     * Filter the value on particular column
     * Eg : name~krishnan
     */
    private String filter;

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


    public String getQuery() {

        return query;
    }


    public void setQuery(String query) {

        this.query = query;
    }


    public OrderingCriteria getSort() {

        return sort;
    }


    public void setSort(OrderingCriteria sort) {

        this.sort = sort;
    }


    public String getColumn() {

        return column;
    }


    public void setColumn(String column) {

        this.column = column;
    }


    public String getFilter() {

        return filter;
    }


    public void setFilter(String filter) {

        this.filter = filter;
    }
}
