# In Memory POSF

Modern day applications can have various sources of data that can extend beyond traditional DBMS. Often, these datasources don't include any advanced features that a typical ORM provides, such as: (**POSF**)
- **P**agination
- **O**rdering
- **S**earching, and 
- **F**iltering 

**In Memory POSF** helps integrate these features into your application when interacting with any such data sources.  
It is source agnostic and can be applied on any situation when the above operations are required on structured(JSON) data is 
necessary.

## Usage

### 1. Import the library into your project

The library is published on [Central repository](https://search.maven.org/artifact/com.techconative/inmemory-posf/1.0.0/jar). 
Import it using your build system.  

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


### 2. Define criteria

**POSFCriteria** class lets you set the criteria.  

`Sample criteria`
```java
POSFCriteria criteria = new POSFCriteria();

// 1.  Filtering and search
criteria.setFilter("multiMedia.[].name=CCCC&*=Vega|vegas&userId=4051");

/* 2. Order filtered data
 * Column name to order by
*/ 
criteria.setColumn("id");
// Ordering criteria. Accepts ASC / DESC.
criteria.setSort(OrderingCriteria.ASC);

/* 3. Paginate ordered data
 * Number of items that need to to displayed in a page. 
 * If it is 0 then it will return all records.
*/  
criteria.setLimit(10);

// 4. Offset page to be returned after paginating the data.
criteria.setPageNumber(1);
```


Below are the conventions for the various operations supported:

#### I. Filter and Search

> Filtering is per column name while search is performed across columns.  
> Multiple values can be supplied at the same time.

##### Operators

| Operator  | Usage     |
|:--:       | --        |
| `&`       | separates multiple filters    |
| `=`       | separates key values (key are column name to be filtered)  |
| `*`       | Represents any column including nested keys as well     |
| `\|`       | separates multiple values in criteria     |
| `[]`      | list of objects |
| `.`       | nested object   |


Setting filter/search criteria :
```java
criteria.setFilter("multiMedia.[].name=CCCC&*=Vega\|vegas&userId=4051");
```

More criteria patterns:
```cucumber
name=sanjay\|neha  
createdAt="2020-01-02T10:54:07.609+00:00  
multiMedia.[].like=10  
*=Vega\|vegas  
counters.[].yearMonth.[].dateCounter.[].likes=10&*=Vega\|vegas  
```

#### II. Ordering

Ordering has two criteria:
- **column** specifies the column used for ordering.
- **sort** specifies the order to use.

```java
criteria.setColumn("columnName");
criteria.setSort(OrderingCriteria.ASC);   // OrderingCriteria.DESC for descending
```


#### III. Pagination

Paginating has two criteria:
- **limit** specifies the max number of results in a page
- **pageNumber** selects the page number to return after pagination

```java
criteria.setLimit(10);
criteria.setPageNumber(1);
``` 


### 3. POSF as a service

- Extend the class **POSFService** from the package **com.techconative.posf.service** and override the **getRawData()**  method. 
- **getRawData()** method must return a list of objects for which POSF operations are to be performed. 

> Supply your data through `getRawData()`:

https://github.com/techconative/inmemory-posf/blob/5be95eca4a8fbaab97cdc5ae4cb0d50cd936ca42/src/test/java/com/techconative/posf/FeedService.java#L23-L34


### 4. Apply criteria and fetch processed data

```java
IPaginationService service = new FeedService();
PageResult pageResult = service.getPageResult(criteria);    // processing data with defined criteria
List<Feed> resultData = pageResult.getData();               // retrieve processed data
```

---
### Alternate approach to using POSF library

If you have limitations in extending our service class don't worry, you can still use our library. 

- **POSFUtil**'s static method **processData()** can be invoked with your list of objects and criteria.

Sample usage:

```java
List rawData;
POSFCriteria criteria;
/*
 * Your Implemenation
 */
PageResult pageResult = POSFUtil.processData(rawData, criteria);    // processing data with defined criteria
List<Feed> resultData = pageResult.getData();                       // retrieve processed data
```
---
### An open source initiative from http://techconative.com
