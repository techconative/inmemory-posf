# inmemory-pagination

## Filter & Search :

Filtering is done by column name while search is performed across columns.
Multiple values can be supplied at the same time.

`&` -> separates column names
`|` -> separates multiple values
`*` -> indicates search across all columns

### Examples:

"name=bharath"
"*=Vega|vegas"
"multiMedia.[].name=CCCC&*=Vega|vegas&userId=4051"

## TODO : 

1. write logic for filtering
2. write logic for searching
3. add enough log statement
4. extend logic to support nested objects ( filtering, search and sort)
5. create excception class
6. Throw exception column name is invalid(if column name is not exist)
7. Write unit testcases (use cucumber)
8. document on how to use with example
9. Finally publish to maven central repo