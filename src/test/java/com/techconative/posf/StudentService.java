package com.techconative.posf;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techconative.posf.model.Student;
import com.techconative.posf.service.POSFService;
import java.io.IOException;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/** StudentService implements library for processing src/test/java/resources/Student.json */
@Slf4j
public class StudentService extends POSFService<Student> {

    private static ObjectMapper mapper = new ObjectMapper();

    @Override
    protected List<Student> getRawData() {
        try {
            return mapper.readValue(
                    this.getClass().getClassLoader().getResourceAsStream("Student.json"),
                    new TypeReference<List<Student>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
