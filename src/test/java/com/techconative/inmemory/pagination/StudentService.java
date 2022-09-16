package com.techconative.inmemory.pagination;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techconative.inmemory.pagination.modal.Student;
import com.techconative.inmemory.pagination.service.PaginationService;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.util.List;


/**
 * StudentService implements library for processing src/test/java/resources/Student.json
 */
@Slf4j
public class StudentService extends PaginationService<Student> {

private static ObjectMapper mapper = new ObjectMapper();

@Override
protected List<Student> getRawData() {
        try {
        return mapper.readValue(this.getClass().getClassLoader()
        .getResourceAsStream("Student.json"),
        new TypeReference<List<Student>>() {
        });
        } catch (IOException e) {
        throw new RuntimeException(e);
        }
        }
        }

