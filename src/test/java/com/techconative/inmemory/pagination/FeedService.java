package com.techconative.inmemory.pagination;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techconative.inmemory.pagination.modal.Feed;
import com.techconative.inmemory.pagination.service.PaginationService;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

/**
 * FeedService implements library for processing src/test/java/resources/Feed_with_500_Records.json
 * <p><a href="https://www.appsloveworld.com/download-sample-json-file-with-multiple-records">Test data provider</a></p>
 */
@Slf4j
public class FeedService extends PaginationService<Feed> {

    private static ObjectMapper mapper = new ObjectMapper();

    @Override
    protected List<Feed> getRawData() {
        try {
            return mapper.readValue(this.getClass().getClassLoader()
                            .getResourceAsStream("Feeds_with_500_Records.json"),
                    new TypeReference<List<Feed>>() {
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
