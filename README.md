### Problem Statement

Struck with Database with no filtering and pagination features?.
UI is feeling Hard to handle bulk of data?.
We provide you the best solution to overcome these issues with minimal overhead.

### Solution

As we are developing so much software in today’s world and dealing with millions and millions of data.
Imagine a situation where you have millions of data to show on your web page.
A browser will have not able to render all the data at one shot.
So the best option is to go for InMemory pagination, where a server will return the bounded number of rows to show in the UI.

### Let’s Learn how to use this plugin

- This plugin is written in JAVA 17 version.
- To use this plugin , just extend the class **PaginationService** and override the **getRawData()**  function such that it returns list of desired object.
- *getRawData()* method will return the complete source data from which the plugin will take care of performing pagination and searching for you.

Sample snippet:

```java
public class FeedService extends PaginationService<Feed> {

  private static ObjectMapper mapper = new ObjectMapper();

    @Override
      protected List<Feed> getRawData() { 

        try {
          return mapper.readValue(this.getClass().getClassLoader()
            .getResourceAsStream("Feeds_with_500_Records.json"),
              new TypeReference<List<Feed>>() {});
        } catch (IOException e) {
          throw new RuntimeException(e);
          }
      }

}
```

<br> <br>

- Plugin provides two class **PaginationCriteria** and **PageResult**  to utilize the  pagination and filtering features on top of the database.

```java
  public class PaginationCriteria {

    /**
     * Number of items that need to to displayed in a page
     * If it is 0 then it will return all records
     */
    private int limit;

    /**
     * Page number
     */
    private int pageNumber;

    /**
     * Column's ordering criteria.
     */
    private OrderingCriteria sort;

    /**
     * Name of the column to sort
     */
    private String column;

    /**
     * Filter the value on particular column
     * Eg : name~krishnan
     */
    private String filter;

}
```

- *PaginationCriteria* helps in setting criteria for pagination depending on your use case.
- Limiting the data return  is done through *setLimit* method in *PaginationCriteria* .
- Data are sorted based on column mentioned in *setColumn* method .
- Sorting can be done in both way (ascending / descending) and is set using *setSort* method using OrderingCriteria (Enum class that our plugin provides).
- PageNumber  is set using *setPageNumber* method .
- Custom Query for Filtering or search can be used with *setFilter* method
- All these methods can be call through instance of **PaginationCriteria** class.

Sample snippet:

```java
        PaginationCriteria criteria = new PaginationCriteria();


        criteria.setFilter("userId=4051"); //filtering
        criteria.setLimit(10);
        criteria.setColumn("id");
        criteria.setSort(OrderingCriteria.ASC);
        criteria.setPageNumber(1);
```

<br> <br>

#### Conventions to follow while setting the custom query

- Search query is set with * in place of column_name unlike we use in filter query.

```java
 criteria.setFilter("*=4051"); //searching
```

- For  filter query column name is given inplace of *.

```java
  criteria.setFilter("userId=4051"); //filtering
```

- Filtering is done by column name while search is performed across columns. Multiple values can be supplied at the same time.

- & -> separates column names .
- | -> separates multiple values and * -> indicates search across all columns .

```java
  criteria.setFilter("multiMedia.[].name=CCCC&*=Vega|vegas&userId=4051");
```

> Note:  Custom query and column name in *setColumn* method is passed as string in double quotes.

- Output for the pagination result is returned as  **PageResult** Class .

snippet:

```java
  public class PageResult<T> {

    /**
     * This represents the total record count
     */
    private int totalCount;

    /**
     * Array of custom object
     */
    private LinkedList<T> data;

    /**
     * This specifies the number of records that need to be returned for a page.
     */
    private int limit;

    /**
     * This specifies the page number
     */
    private int pageNumber;

    /**
     * This specified the number of records matched with search text
     */
    private int filteredCount;

}
```

- Pagination service is called by instantiating object for the class that extends it.
- pagination criteria is set and passed as argument to Pagination service.
- Page Result is used to display the resulting desired list of objects.

snippets:

```java
        PageResult pageResult = paginationService.getPageResult(criteria);

        log.info("pageResult size = " + pageResult.getData().size());//displays filtered data size
        log.info("pageResult data = " + pageResult.getData());//displays filtered data 
        log.info("pageResult total count = " + pageResult.getTotalCount());//displays filtered data total count
```