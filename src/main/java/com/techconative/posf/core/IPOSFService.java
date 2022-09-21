package com.techconative.posf.core;

import com.techconative.posf.model.PageResult;
import com.techconative.posf.model.POSFCriteria;

/**
 * Interface of main class to be implemented to use the library
 *
 * @author Krishnan - Techconative
 */
public interface IPOSFService {

    /**
     * The central method to extend the library as a service.
     *
     * @param criteria contains filter, search, sort and pagination constraints
     * @return PageResult of the provided data as per constraints
     * @since 1.0.0
     */
    PageResult getPageResult(POSFCriteria criteria);
}
