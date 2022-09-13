package com.techconative.inmemory.pagination.core;

import com.techconative.inmemory.pagination.modal.PageResult;
import com.techconative.inmemory.pagination.modal.PaginationCriteria;


/**
 * @author Krishnan - Techconative
 *
 */
public interface IPaginationService {

    PageResult getPageResult(PaginationCriteria criteria);

}
