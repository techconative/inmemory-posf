package com.techconative.inmemory.pagination.usage;

public class Emp {

    private String empName;
    private int empId;
    private long salary;
    private String email;
    private Department dept;


    public Emp(String empName, int empId, long salary, String email, Department dept) {

        this.empName = empName;
        this.empId = empId;
        this.salary = salary;
        this.email = email;
        this.dept = dept;
    }
}
