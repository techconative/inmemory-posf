package com.techconative.inmemory.pagination;

import com.techconative.inmemory.pagination.core.IPaginationService;
import com.techconative.inmemory.pagination.modal.OrderingCriteria;
import com.techconative.inmemory.pagination.modal.PageResult;
import com.techconative.inmemory.pagination.modal.PaginationCriteria;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CucumberStepDefinitions contains java implementation for tests written in Cucumber feature file
 */
@Slf4j
public class CucumberStepDefinitions {

    PageResult pageResult = null;
    PaginationCriteria criteria = null;

    IPaginationService t = null;

    @Given("Feed data")
    public void feedData() {
        t = new FeedService();
    }

    @Given("Counter Data")
    public void counterData() {
        t = new CounterService();
    }

    @Given("Student Data")
    public void StudentData() {
        t = new StudentService();
    }

    @And("Default Criteria")
    public void defaultCriteria() {
        criteria = new PaginationCriteria();

        criteria.setLimit(100);
        criteria.setColumn("id");
        criteria.setSort(OrderingCriteria.ASC);
        criteria.setPageNumber(1);
    }

    @Then("Result size should be {int}")
    public void resultSizeShouldBe(int size) {
        pageResult = t.getPageResult(criteria);
        Assertions.assertEquals(size, pageResult.getFilteredCount());
    }


    @When("{string} has {string}")
    public void keyHasValue(String key, String value) {
        criteria.setFilter(key + "=" + value);
    }

    @When("Search terms are {string}")
    public void searchTermsAre(String query) {
        criteria.setFilter("*=" + query);
    }

    @When("Complex filtering criteria is {string}")
    public void complexFilteringCriteriaIs(String query) {
        criteria.setFilter(query);
    }

    @And("{string} present in {string}")
    public void objidPresentInExpectedObjs(String objId, String expectedObjects) {
        ArrayList resultantList = (ArrayList) pageResult.getData().stream().map(row -> ((LinkedHashMap) row).get(objId).toString()).collect(Collectors.toList());
        if (resultantList.size() > 0) {
            List<String> expectedObjsList = Arrays.stream(expectedObjects.split(",")).sorted().collect(Collectors.toList());
            Assertions.assertTrue(resultantList.equals(expectedObjsList));
        }
    }

}