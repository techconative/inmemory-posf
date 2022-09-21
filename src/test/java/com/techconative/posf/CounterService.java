package com.techconative.posf;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techconative.posf.model.Counters;
import com.techconative.posf.service.POSFService;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/** CounterService implements library for processing src/test/java/resources/CounterData.json */
@Slf4j
public class CounterService extends POSFService<Counters> {

    private static ObjectMapper mapper = new ObjectMapper();

    @Override
    protected List<Counters> getRawData() {
        try {
            return mapper.readValue(
                    this.getClass().getClassLoader().getResourceAsStream("CounterData.json"),
                    new TypeReference<List<Counters>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
