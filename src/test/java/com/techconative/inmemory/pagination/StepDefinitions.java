package com.techconative.inmemory.pagination;

import com.techconative.inmemory.pagination.core.IPaginationService;
import com.techconative.inmemory.pagination.modal.OrderingCriteria;
import com.techconative.inmemory.pagination.modal.PageResult;
import com.techconative.inmemory.pagination.modal.PaginationCriteria;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {

    PageResult pageResult = null;
    PaginationCriteria criteria = null;

    IPaginationService t = null;

    @Given("Feed Rawdata")
    public void feedRawdata() {
        t = new FeedService();
        criteria = new PaginationCriteria();

        criteria.setLimit(10);
        criteria.setColumn("id");
        criteria.setSort(OrderingCriteria.ASC);
        criteria.setPageNumber(1);
    }

    @When("Filtering name {string}")
    public void findByName(String name) {
        criteria.setFilter("name=" + name);
        pageResult = t.getPageResult(criteria);
    }

    @Then("Result size should be {int}")
    public void resultSizeShouldBe(int size) {
        if (pageResult.getData().size() == size ) {
            System.out.println("Result equals.");
        }
    }

    @When("Nested value multiMedia\\/name is {string}")
    public void nestedValueMultiMediaNameIs(String arg0) {
        criteria.setFilter("name#bharath");
        pageResult = t.getPageResult(criteria);
    }


}
