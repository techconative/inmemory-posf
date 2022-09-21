# In Memory-POSF


Modern day application's datasource can cut across corners apart from DB and at times these datasources dosen't provide out of the box features that are provided in ORM like **P**agination, **O**rdering, **S**earching and **F**iltering (**POSF**) features. **In Memory POSF** library has out of the box features that can simplify your task of implementing these requirements.

### Import the library into your project

> `maven`

```xml
<!-- pom.xml -->
<dependency>
  <groupId>com.techconative</groupId>
  <artifactId>inmemory-posf</artifactId>
  <version>1.0.0</version>
</dependency>
```
> `gradle`

```gradle
// build.gradle
dependencies {
  implementation 'com.techconative.inmemory-posf:1.0.0'
}
```
### Easy to use

#### Extend service class

- Extend the class **PaginationService** from the package **com.techconative.posf.service** and override the **getRawData()**  method. 
- **getRawData()** method will return the list of objects for which POSF operations are to be performed. 

Sample snippet:

https://github.com/techconative/inmemory-posf/blob/5be95eca4a8fbaab97cdc5ae4cb0d50cd936ca42/src/test/java/com/techconative/posf/FeedService.java#L23-L34

#### Define criteria

**POSFCriteria** class can be used to set the criteria. Please follow the conventions for various operations as below:

##### Conventions to follow while setting the custom query for searching

- Search query is set with * in place of column_name unlike we use in filter query.

```java
 criteria.setFilter("*=4051"); //searching
 criteria.setFilter("*=4051|5021"); //searching multiple values using or
```

##### Conventions to follow while setting the custom query for filtering

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
#### Conventions to follow while setting the custom query for sorting


```java
   criteria.setSort(OrderingCriteria.ASC);   /// OrderingCriteria.DESC for descending
```

> Note:  Custom query and column name in *setColumn* method is passed as string in double quotes.

#### Conventions to follow while setting the custom query for pagination and result

```java
 criteria.setLimit(10);
 criteria.setPageNumber(1);
``` 

**Example**
```java
    POSFCriteria criteria = new POSFCriteria();

    // 1.  Filtering and search
    criteria.setFilter("multiMedia.[].name=CCCC&*=Vega|vegas&userId=4051");

    /* 2. Sort filtered data
     * Column name to sort by
    */ 
    criteria.setColumn("id");
    // Sorting criteria. Accepts ASC / DESC.
    criteria.setSort(OrderingCriteria.ASC);

    /* 3. Paginate sorted data
     * Number of items that need to to displayed in a page. 
     * If it is 0 then it will return all records.
    */  
    criteria.setLimit(10);

    // 4. Offset page to be returned after paginating the data.
    criteria.setPageNumber(1);
```

#### Apply criteria & Using the Results

```java
IPaginationService service = new FeedService();
PageResult pageResult = service.getPageResult(criteria);
List<Feed> resultData = pageResult.getData();
```

#### Alternate Approach

If you have limitations to extend our service class don't worry you can still use our library. 

- **POSFUtil** static method **processData** can be invoked along with your list of objects and the criteria.

Sample usage:

```java
  List rawData;
  POSFCriteria criteria;
  /*
   * Your Implemenation
   */
  POSFUtil.processData(rawData, criteria);
```