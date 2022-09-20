package com.techconative.inmemory.pagination.service;

import com.techconative.inmemory.pagination.core.IPaginationService;
import com.techconative.inmemory.pagination.modal.PageResult;
import com.techconative.inmemory.pagination.modal.PaginationCriteria;
import com.techconative.inmemory.pagination.util.InmemoryFOPS;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Filter, search, sort and paginate your data.<br/>
 * PaginationService is the class to be extended to utilise the library.
 *
 * @see <a href="https://github.com/techconative/inmemory-pagination">Git repository</a> for usage.
 */
@Slf4j
public abstract class PaginationService<T> implements IPaginationService {

    /**
     * The central method to invoke the library as a service.
     *
     * @param criteria contains filter, search, sort and pagination constraints
     * @return PageResult of the provided data as per constraints
     * @since 1.0.0
     */
    @Override
    public PageResult getPageResult(PaginationCriteria criteria) {

        List<T> rawData = getRawData();

        return InmemoryFOPS.processData(rawData, criteria);
    }

    /**
     * Fetch data for processing<br/>
     * Abstract method to be implemented.
     *
     * @return List of data
     * @since 1.0.0
     */
    protected abstract List<T> getRawData();
}
