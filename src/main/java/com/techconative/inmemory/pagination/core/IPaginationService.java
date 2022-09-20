package com.techconative.inmemory.pagination.core;

import com.techconative.inmemory.pagination.modal.PageResult;
import com.techconative.inmemory.pagination.modal.PaginationCriteria;

/**
 * Interface of main class to be implemented to use the library
 *
 * @author Krishnan - Techconative
 */
public interface IPaginationService {

    /**
     * The central method to extend the library as a service.
     *
     * @param criteria contains filter, search, sort and pagination constraints
     * @return PageResult of the provided data as per constraints
     * @since 1.0.0
     */
    PageResult getPageResult(PaginationCriteria criteria);
}
