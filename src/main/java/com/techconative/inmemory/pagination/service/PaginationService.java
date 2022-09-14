package com.techconative.inmemory.pagination.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.techconative.inmemory.pagination.core.IPaginationService;
import com.techconative.inmemory.pagination.modal.OrderingCriteria;
import com.techconative.inmemory.pagination.modal.PageResult;
import com.techconative.inmemory.pagination.modal.PaginationCriteria;
import exception.InvalidCriteriaException;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public abstract class PaginationService<T> implements IPaginationService {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public PageResult getPageResult(PaginationCriteria criteria) {

        PageResult pageResult = new PageResult();

        List<Map<String, String>> rawData = convert(getRawData());


        //Step 1 : Apply filtering
        //filter=firstName:krishnan~lastname:gopal
        // multiMedia.name = krishnan and lastname = gopal
        if (criteria.getFilter() != null && !criteria.getFilter().isEmpty()) {
            rawData = applyFiltering(criteria, rawData);
        }

        //Step 2 : Apply sorting
        LinkedList<Map<String, String>> sortedList = applySorting(criteria, rawData);

        //Step 3 : Apply limit and offset
        LinkedList<Map<String, String>> resultList = applyPagination(criteria, sortedList);


        pageResult.setData(resultList);
        pageResult.setLimit(criteria.getLimit());
        pageResult.setPageNumber(criteria.getPageNumber());
        pageResult.setFilteredCount(resultList.size());
        pageResult.setTotalCount(rawData.size());

        return pageResult;
    }


    private List<Map<String, String>> convert(List rawData) {

        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        return rawData.stream().map(item -> objectMapper.convertValue(item, Map.class)).toList();
    }

    private List<Map<String, String>> applyFiltering(PaginationCriteria criteria, List<Map<String, String>> rawData) {
        Map<String, String> filterMap = convertFilterCriteria(criteria.getFilter());
        return rawData.stream().filter(row -> filterRow(row, filterMap)).toList();
    }

    private boolean filterRow(Map<String, String> row, Map<String, String> criteriaMap) {
        return criteriaMap.entrySet().stream().allMatch(e -> matchRow(e.getKey(), e.getValue(), row));
    }

    private static boolean matchRow(String masterKey, String value, Object row) {
        if (masterKey.equals("*")) {
            return searchForValue(value, row);
        }
        String[] keys = masterKey.split("\\.", 2);
        if (keys[0].equals("[]")) {
            if (row instanceof ArrayList<?> listOfValues) {
                return (listOfValues).stream().anyMatch(obj -> matchRow(keys[1], value, obj));
            } else {
                throw new InvalidCriteriaException("Array not found.");
            }
        } else {
            if (keys.length == 1) {
                return checkEquality("equals", ((LinkedHashMap<?, ?>)row).get(keys[0]).toString(), value);
            } else {
                return matchRow(keys[1], value, ((LinkedHashMap<?, ?>)row).get(keys[0]));
            }
        }
    }

    private static boolean searchForValue(String value, Object row) {
        if (row instanceof ArrayList<?> listOfValues) {
            return (listOfValues).stream().anyMatch(obj -> searchForValue(value, obj));
        } else if (row instanceof LinkedHashMap<?, ?> mapOfValues) {
            return (mapOfValues).values().stream().anyMatch(entry -> searchForValue(value, entry));
        } else {
            if(row != null) {
                return checkEquality("contains", row.toString(), value);
            }
            return false;
        }
    }

    private static boolean checkEquality(String op, String v1, String v2) {
        if (op.equals("equals")) {
            return Arrays.asList(v2.split("\\|")).contains(v1);
        } else if (op.contains("contains")) {
            return Arrays.stream(v2.split("\\|")).anyMatch(v1::contains);
        }
        return false;
    }

    public static Map<String, String> convertFilterCriteria(String filter) {
        try {
            //TODO: in case of duplicate keys, only the first occurrence is used
            //TODO: implement filtering by multiple value in a given column using OR ?
            return Stream.of(filter.split("&")).map(str -> str.split("=")).collect(Collectors.toMap(str -> str[0], str -> str[1], (a, b) -> a, LinkedHashMap::new));
        } catch (ArrayIndexOutOfBoundsException e) {
            log.info("Invalid input. Exception occurred.");
            throw new InvalidCriteriaException("Invalid input criteria. Exception processing filter criteria.", e);
        }
    }

    private LinkedList<Map<String, String>> applySorting(PaginationCriteria criteria, List<Map<String, String>> filteredList) {
        LinkedList<Map<String, String>> sortedList = null;
        if (criteria.getSort().equals(OrderingCriteria.DESC)) {
            sortedList = filteredList.stream().sorted(Comparator.comparing(m -> String.valueOf(m.get(criteria.getColumn())), Comparator.nullsFirst(Comparator.reverseOrder()))).collect(Collectors.toCollection(LinkedList::new));
        } else {
            sortedList = filteredList.stream().sorted(Comparator.comparing(m -> String.valueOf(m.get(criteria.getColumn())), Comparator.nullsFirst(Comparator.naturalOrder()))).collect(Collectors.toCollection(LinkedList::new));
        }
        return sortedList;
    }

    private LinkedList applyPagination(PaginationCriteria criteria, LinkedList sortedList) {
        if (criteria.getLimit() <= 0) {
            return sortedList;
        }
        int skipCount = (criteria.getPageNumber() - 1) * criteria.getLimit();
        return (LinkedList) sortedList.stream().skip(skipCount).limit(criteria.getLimit()).collect(Collectors.toCollection(LinkedList::new));
    }


    protected abstract List<T> getRawData();
}
