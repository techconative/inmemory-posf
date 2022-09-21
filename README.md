# In Memory-POSF


### Problem Statement

Struck with Datasource with no filtering,searching and pagination features?.
UI is feeling Hard to handle bulk of data?.
We provide you the best solution to overcome these issues with minimal overhead.

### Solution

As we are developing so much software in today’s world and dealing with tons of data.
Imagine a situation where you have tons of data to show on your web page.
A browser will have not able to render all the data at one shot.
So the best option is to go for InMemory pagination, where a server will return the bounded number of rows to show in the UI.


### How to use this plugin

##### Approach 1

- To use this plugin , just extend the class **PaginationService** and override the **getRawData()**  function such that it returns list of desired object.
- *getRawData()* method will return the complete source data from which the plugin will take care of performing pagination and searching for you.

Sample snippet:

https://github.com/techconative/inmemory-posf/blob/536ba7b1062edce986798a96c7c7210302921807/src/test/java/com/techconative/inmemory/pagination/FeedService.java#L23-L34

<br> <br>

##### Approach 2

- Directly call static methods of class InmemoryFOPS Class that the plugin provides by default.
- use *applyFiltering*  which is static method in InmemoryFOPS for filtering and searching.
- it takes List of Map and PaginationCriteria as parameters.
- Can be used anywhere in your program depending on your use cases.

Sample usage:

```java
  InmemoryFOPS.applyFiltering( PaginationCriteria criteria, List<Map<String, String>> rawData)
```
 
- Same way ,we can call *applySorting* and *applyPagination* static method of InmemoryFOPS to leverage its benefits.
- PaginationCriteria changes for each use case.

```java
  InmemoryFOPS. applySorting(PaginationCriteria criteria, List<Map<String, String>> rawData);
  InmemoryFOPS. applyPagination(PaginationCriteria criteria, List<Map<String, String>> rawData);
```
<br> <br>

- Plugin provides two class **PaginationCriteria** and **PageResult**  to utilize the  pagination and filtering features on top of the database.

https://github.com/techconative/inmemory-posf/blob/77c1371e8993193dbf0aac299137aec888aa6c56/src/main/java/com/techconative/inmemory/pagination/modal/PaginationCriteria.java#L9-L28

- *PaginationCriteria* helps in setting criteria for pagination depending on your use case.
- Limiting the data return  is done through *setLimit* method in *PaginationCriteria* .
- Data are sorted based on column mentioned in *setColumn* method .
- ***Sorting*** can be done in both way (ascending / descending) and is set using *setSort* method using OrderingCriteria (Enum class that our plugin provides).
- PageNumber  is set using *setPageNumber* method .
- Custom Query for ***Filtering*** or ***searching*** can be used with *setFilter* method
- All these methods can be call through instance of **PaginationCriteria** class.

Sample snippet:

https://github.com/techconative/inmemory-posf/blob/536ba7b1062edce986798a96c7c7210302921807/src/test/java/com/techconative/inmemory/pagination/CucumberStepDefinitions.java#L65-L75

https://github.com/techconative/inmemory-posf/blob/536ba7b1062edce986798a96c7c7210302921807/src/test/resources/cucumber/pagination/FeedTest.feature#L14-L31

<br> <br>

### Conventions to follow while setting the custom query for searching

- Search query is set with * in place of column_name unlike we use in filter query.

```java
 criteria.setFilter("*=4051"); //searching
 criteria.setFilter("*=4051|5021"); //searching multiple values using or
```

### Conventions to follow while setting the custom query for filtering

- For  filter query column name is given inplace of *.

```java
  criteria.setFilter("userId=4051"); //filtering
```

- Filtering is done by column name while search is performed across columns. Multiple values can be supplied at the same time.

- & -> separates column names .
- \\| -> separates multiple values and * -> indicates search across all columns .
- [] -> is placed after every List.
- . -> represents nested

```java
  criteria.setFilter("multiMedia.[].name=CCCC&*=Vega\|vegas&userId=4051");
```
### Conventions to follow while setting the custom query for sorting


```java
   criteria.setSort(OrderingCriteria.ASC);   /// OrderingCriteria.DESC for descending
```

> Note:  Custom query and column name in *setColumn* method is passed as string in double quotes.

### Conventions to follow while setting the custom query for pagination and result

```java
 criteria.setLimit(10);
 criteria.setPageNumber(1);
```

- Output for the pagination result is returned as  **PageResult** Class .

snippet:

https://github.com/techconative/inmemory-posf/blob/536ba7b1062edce986798a96c7c7210302921807/src/main/java/com/techconative/inmemory/pagination/modal/PageResult.java#L11-L27

- *getPageResult* method is called by instantiating object for the class that extends it.


https://github.com/techconative/inmemory-posf/blob/536ba7b1062edce986798a96c7c7210302921807/src/test/java/com/techconative/inmemory/pagination/CucumberStepDefinitions.java#L58

- pagination criteria is set and passed as argument to Pagination service.

https://github.com/techconative/inmemory-posf/blob/536ba7b1062edce986798a96c7c7210302921807/src/test/java/com/techconative/inmemory/pagination/CucumberStepDefinitions.java#L48-L53

- Page Result is used to display the resulting desired list of objects.

https://github.com/techconative/inmemory-posf/blob/536ba7b1062edce986798a96c7c7210302921807/src/test/java/com/techconative/inmemory/pagination/CucumberStepDefinitions.java#L80-L82
