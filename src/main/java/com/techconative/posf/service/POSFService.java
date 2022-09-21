package com.techconative.posf.service;

import com.techconative.posf.core.IPOSFService;
import com.techconative.posf.model.PageResult;
import com.techconative.posf.model.POSFCriteria;
import com.techconative.posf.util.POSFUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Filter, search, sort and paginate your data.<br/>
 * POSFService is the class to be extended to utilise the library.
 *
 * @see <a href="https://github.com/techconative/inmemory-posf">Git repository</a> for usage.
 */
@Slf4j
public abstract class POSFService<T> implements IPOSFService {

    /**
     * The central method to invoke the library as a service.
     *
     * @param criteria contains filter, search, sort and pagination constraints
     * @return PageResult of the provided data as per constraints
     * @since 1.0.0
     */
    @Override
    public PageResult getPageResult(POSFCriteria criteria) {

        List<T> rawData = getRawData();

        return POSFUtil.processData(rawData, criteria);
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
