package com.techconative.posf;

import com.techconative.posf.core.IPOSFService;
import com.techconative.posf.model.OrderingCriteria;
import com.techconative.posf.model.PageResult;
import com.techconative.posf.model.POSFCriteria;
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
    POSFCriteria criteria = null;

    IPOSFService t = null;

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
        criteria = new POSFCriteria();

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

    @And("Object identifier {string} is present in {string}")
    public void objectIdentifierObjIDIsPresentInExpectedObjsList(String objId, String expectedObjects) {
        List resultantList = (ArrayList) pageResult.getData().stream()
                .map(row -> ((LinkedHashMap) row).get(objId).toString())
                .collect(Collectors.toList());
        if (resultantList.size() > 0) {
            List<String> expectedObjsList = Arrays.stream(expectedObjects.split(",")).sorted().collect(Collectors.toList());
            Assertions.assertEquals(resultantList, expectedObjsList);
        }
    }

}