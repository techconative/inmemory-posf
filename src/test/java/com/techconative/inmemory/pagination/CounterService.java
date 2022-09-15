package com.techconative.inmemory.pagination;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techconative.inmemory.pagination.modal.*;
import com.techconative.inmemory.pagination.service.PaginationService;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

/**
 * CounterService implements library for processing src/test/java/resources/CounterData.json
 */
@Slf4j
public class CounterService extends PaginationService<Counters> {

    private static ObjectMapper mapper = new ObjectMapper();

    @Override
    protected List<Counters> getRawData() {
        try {
            return mapper.readValue(this.getClass().getClassLoader()
                            .getResourceAsStream("CounterData.json"),
                    new TypeReference<List<Counters>>() {
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
