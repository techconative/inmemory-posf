# In Memory Pagination


### Problem Statement

Struck with Database with no filtering,searching and pagination features?.
UI is feeling Hard to handle bulk of data?.
We provide you the best solution to overcome these issues with minimal overhead.

### Solution

As we are developing so much software in today’s world and dealing with tons of data.
Imagine a situation where you have tons of data to show on your web page.
A browser will have not able to render all the data at one shot.
So the best option is to go for InMemory pagination, where a server will return the bounded number of rows to show in the UI.


### How to use this plugin

- To use this plugin , just extend the class **PaginationService** and override the **getRawData()**  function such that it returns list of desired object.
- *getRawData()* method will return the complete source data from which the plugin will take care of performing pagination and searching for you.

Sample snippet:

https://github.com/techconative/inmemory-pagination/blob/d2199f113ad935eadefaeb5b7943fdbc17c321c0/src/test/java/com/techconative/inmemory/pagination/FeedService.java#L17-L32

<br> <br>

- Plugin provides two class **PaginationCriteria** and **PageResult**  to utilize the  pagination and filtering features on top of the database.

https://github.com/techconative/inmemory-pagination/blob/d2199f113ad935eadefaeb5b7943fdbc17c321c0/src/main/java/com/techconative/inmemory/pagination/modal/PaginationCriteria.java#L1-L37

- *PaginationCriteria* helps in setting criteria for pagination depending on your use case.
- Limiting the data return  is done through *setLimit* method in *PaginationCriteria* .
- Data are sorted based on column mentioned in *setColumn* method .
- ***Sorting*** can be done in both way (ascending / descending) and is set using *setSort* method using OrderingCriteria (Enum class that our plugin provides).
- PageNumber  is set using *setPageNumber* method .
- Custom Query for ***Filtering*** or ***searching*** can be used with *setFilter* method
- All these methods can be call through instance of **PaginationCriteria** class.

Sample snippet:

(https://github.com/techconative/inmemory-pagination/blob/c16d8ee33307156664a961b30813548d922dd37e/src/test/java/com/techconative/inmemory/pagination/CucumberStepDefinitions.java#L25-L33)

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
- | -> separates multiple values and * -> indicates search across all columns .
- [] -> is placed after every List.
- . -> represents nested

```java
  criteria.setFilter("multiMedia.[].name=CCCC&*=Vega|vegas&userId=4051");
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

https://github.com/techconative/inmemory-pagination/blob/d2199f113ad935eadefaeb5b7943fdbc17c321c0/src/main/java/com/techconative/inmemory/pagination/modal/PageResult.java#L1-L37

- Pagination service is called by instantiating object for the class that extends it.
- pagination criteria is set and passed as argument to Pagination service.
- Page Result is used to display the resulting desired list of objects.

snippets:

https://github.com/techconative/inmemory-pagination/blob/c16d8ee33307156664a961b30813548d922dd37e/src/test/java/com/techconative/inmemory/pagination/CucumberStepDefinitions.java#L59-L60
