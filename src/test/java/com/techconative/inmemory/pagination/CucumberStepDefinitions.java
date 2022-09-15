package com.techconative.inmemory.pagination;

import com.techconative.inmemory.pagination.core.IPaginationService;
import com.techconative.inmemory.pagination.modal.OrderingCriteria;
import com.techconative.inmemory.pagination.modal.PageResult;
import com.techconative.inmemory.pagination.modal.PaginationCriteria;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;

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
        criteria = new PaginationCriteria();

        criteria.setLimit(10);
        criteria.setColumn("id");
        criteria.setSort(OrderingCriteria.ASC);
        criteria.setPageNumber(1);
    }

    @Given("Counter Data")
    public void counterData() {
        t = new CounterService();
        criteria = new PaginationCriteria();

        criteria.setLimit(10);
        criteria.setColumn("id");
        criteria.setSort(OrderingCriteria.ASC);
        criteria.setPageNumber(1);
    }

    @When("Filtering name {string}")
    public void findByName(String name) {
        criteria.setFilter("name=" + name);
    }

    @Then("Result size should be {int}")
    public void resultSizeShouldBe(int size) {
        pageResult = t.getPageResult(criteria);
        Integer resultSize = pageResult.getTotalCount();
        if ( resultSize == size ) {
            log.info("Total results found : " + resultSize + ". Matches expected.");
        } else {
            log.error("Expected results not found. Test failed.");
        }
        Assertions.assertEquals(size, resultSize);
    }

    @When("Key value {string} is {string}")
    public void keyValueIs(String key, String value) {
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
}
