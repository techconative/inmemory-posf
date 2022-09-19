package com.techconative.inmemory.pagination.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.techconative.inmemory.pagination.modal.OrderingCriteria;
import com.techconative.inmemory.pagination.modal.PageResult;
import com.techconative.inmemory.pagination.modal.PaginationCriteria;
import exception.InvalidCriteriaException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;

/**
 * Filter, search, sort and paginate your data. PaginationService is the class to be extended to
 * utilise the library
 *
 * @see <a href="https://github.com/techconative/inmemory-pagination">Git repository</a> for usage.
 */
@Slf4j
public class InmemoryFOPS {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * The central method which gets the data and apply filter, search, sort and paginates based on
     * criteria
     *
     * @param rawData input data to apply criteria operations on
     * @param criteria contains filter, search, sort and pagination constraints
     * @return List of result data with constraints applied
     * @since 1.0.0
     */
    public static PageResult processData(List rawData, PaginationCriteria criteria) {
        List<Map<String, String>> data = convert(rawData);

        if (criteria.getFilter() != null && !criteria.getFilter().isEmpty()) {
            data = applyFiltering(criteria, data);
        }

        LinkedList<Map<String, String>> sortedList = applySorting(criteria, data);

        LinkedList resultList = applyPagination(criteria, sortedList);

        PageResult pageResult = new PageResult();
        pageResult.setData(resultList);
        pageResult.setLimit(criteria.getLimit());
        pageResult.setPageNumber(criteria.getPageNumber());
        pageResult.setFilteredCount(sortedList.size());
        pageResult.setTotalCount(rawData.size());

        return pageResult;
    }

    /**
     * Converts user objects to List of Map of Strings
     *
     * @param rawData List of user object
     * @return List of Map of Strings
     * @since 1.0.0
     */
    private static List<Map<String, String>> convert(List rawData) {

        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        return (List<Map<String, String>>)
                rawData.stream()
                        .map(item -> objectMapper.convertValue(item, Map.class))
                        .collect(Collectors.toList());
    }

    /**
     * Filter and search each row of data based on criteria
     *
     * @param criteria defines the filtering and search column and value
     * @param rawData provides the data
     * @return filtered data as per given criteria
     * @since 1.0.0
     */
    private static List<Map<String, String>> applyFiltering(
            PaginationCriteria criteria, List<Map<String, String>> rawData) {
        Map<String, Set<Pattern>> filterMap = convertFilterCriteria(criteria.getFilter());
        return rawData.stream()
                .filter(row -> filterRow(row, filterMap))
                .collect(Collectors.toList());
    }

    /**
     * Iterates each filter criteria for each given row of data
     *
     * <p>Row is included in filtered result if all criteria is satisfiend
     *
     * @param row defines single element in list of data
     * @param criteriaMap map of all filter and search criteria
     * @return data after search and column filtering is applied
     * @since 1.0.0
     */
    private static boolean filterRow(Map<String, String> row, Map<String, Set<Pattern>> criteriaMap) {
        return criteriaMap.entrySet().stream()
                .allMatch(e -> matchRow(e.getKey(), e.getValue(), row));
    }

    /**
     * Matches each criterion on every row
     *
     * @param masterKey column name
     * @param value criteria to be filtered or searched
     * @param row defines single element in list of data
     * @return boolean result if criteria value is matched in column
     * @since 1.0.0
     */
    private static boolean matchRow(String masterKey, Set<Pattern> value, Object row) {
        if (masterKey.equals("*")) {
            return searchForValue(value, row);
        }
        String[] keys = masterKey.split("\\.", 2);
        if (keys[0].equals("[]")) {
            if (row instanceof ArrayList<?>) {
                return ((List) row).stream().anyMatch(obj -> matchRow(keys[1], value, obj));
            } else {
                throw new InvalidCriteriaException("Array not found.");
            }
        } else {
            if (keys.length == 1) {
                return matchValues(((LinkedHashMap<?, ?>) row).get(keys[0]), value);
            } else {
                return matchRow(keys[1], value, ((LinkedHashMap<?, ?>) row).get(keys[0]));
            }
        }
    }

    /**
     * Search for a given value in all columns of data
     *
     * @param value to be searched for
     * @param row of the data supplied
     * @return boolean result whether a row contains the searched value
     * @since 1.0.0
     */
    private static boolean searchForValue(Set<Pattern> value, Object row) {
        if (row instanceof ArrayList<?>) {
            return ((List) row).stream().anyMatch(obj -> searchForValue(value, obj));
        } else if (row instanceof LinkedHashMap<?, ?>) {
            return ((LinkedHashMap) row)
                    .values().stream().anyMatch(entry -> searchForValue(value, entry));
        } else {
            return matchValues(row, value);
        }
    }

    /**
     * Checks if supplied value contains the string
     *
     * @param matcher supplied data to be filtered
     * @param pattern value being filtered by or searched for
     * @return boolean result
     * @since 1.0.0
     */
    private static boolean matchValues(Object matcher, Set<Pattern> pattern) {
        if (matcher != null && pattern != null) {
            return pattern.stream()
                    .anyMatch(pat -> pat.matcher(matcher.toString()).find());
        }
        return false;
    }

    /**
     * Converts filter and search criteria into Map
     *
     * @param filter criteria
     * @return Map of filters to be applied
     * @since 1.0.0
     */
    private static Map<String, Set<Pattern>> convertFilterCriteria(String filter) {
        try {
            return Stream.of(filter.split("&"))
                    .map(str -> str.split("="))
                    .collect(
                            Collectors.toMap(
                                    str -> str[0], str -> strToPattern(str[1]), (a, b) -> a, LinkedHashMap::new));
        } catch (ArrayIndexOutOfBoundsException e) {
            log.info("Invalid input. Exception occurred.");
            throw new InvalidCriteriaException(
                    "Invalid input criteria. Exception processing filter criteria.", e);
        }
    }

    /**
     * Split string of values into Set of Patterns for matching
     *
     * @param str of values to match
     * @return Map of Set of values for filtering
     * @since 1.0.0
     */
    private static Set<Pattern> strToPattern(String str) {
        return new HashSet<>(
                Arrays.stream(str.split("\\|"))
                        .map(s->Pattern.compile(Pattern.quote(s), Pattern.CASE_INSENSITIVE))
                        .collect(Collectors.toList()));
    }

    /**
     * Sorts the data per ordering specified in criteria
     *
     * @param criteria defines the sorting limit
     * @param filteredList is the data after filtering
     * @return List of data
     * @since 1.0.0
     */
    private static LinkedList<Map<String, String>> applySorting(
            PaginationCriteria criteria, List<Map<String, String>> filteredList) {
        LinkedList<Map<String, String>> sortedList = null;
        if (criteria.getSort().equals(OrderingCriteria.DESC)) {
            sortedList =
                    filteredList.stream()
                            .sorted(
                                    Comparator.comparing(
                                            m -> String.valueOf(m.get(criteria.getColumn())),
                                            Comparator.nullsFirst(Comparator.reverseOrder())))
                            .collect(Collectors.toCollection(LinkedList::new));
        } else {
            sortedList =
                    filteredList.stream()
                            .sorted(
                                    Comparator.comparing(
                                            m -> String.valueOf(m.get(criteria.getColumn())),
                                            Comparator.nullsFirst(Comparator.naturalOrder())))
                            .collect(Collectors.toCollection(LinkedList::new));
        }
        return sortedList;
    }

    /**
     * Paginates the data per limit specified in criteria
     *
     * @param criteria defines the pagination limit
     * @param sortedList is the data after sorting
     * @return List of data
     * @since 1.0.0
     */
    private static LinkedList applyPagination(PaginationCriteria criteria, LinkedList sortedList) {
        if (criteria.getLimit() <= 0) {
            return sortedList;
        }
        int skipCount = (criteria.getPageNumber() - 1) * criteria.getLimit();
        return (LinkedList)
                sortedList.stream()
                        .skip(skipCount)
                        .limit(criteria.getLimit())
                        .collect(Collectors.toCollection(LinkedList::new));
    }
}
