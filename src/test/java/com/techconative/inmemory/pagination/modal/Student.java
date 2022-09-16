package com.techconative.inmemory.pagination.modal;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Student {

    @JsonProperty("ID")
    public String id;
    @JsonProperty("LastName")
    public String lastName;
    @JsonProperty("FirstName")
    public String firstName;
    @JsonProperty("Email")
    public String mail;
    @JsonProperty("City")
    public String city;
    @JsonProperty("State")
    public String state;
    @JsonProperty("Gender")
    public String gender;
    @JsonProperty("StudentStatus")
    public String studentStatus;
    @JsonProperty("Major")
    public String major;
    @JsonProperty("Country")
    public String country;
    @JsonProperty("Age")
    public String age;
    @JsonProperty("SAT")
    public String sAT;
    @JsonProperty("Grade ")
    public String grade;
    @JsonProperty("Height")
    public String height;


    public String getId() {

        return id;
    }


    public void setId(String id) {

        this.id = id;
    }


    public String getLastName() {

        return lastName;
    }


    public void setLastName(String lastName) {

        this.lastName = lastName;
    }


    public String getFirstName() {

        return firstName;
    }


    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }


    public String getCity() {

        return city;
    }


    public void setCity(String city) {

        this.city = city;
    }


    public String getState() {

        return state;
    }


    public void setState(String state) {

        this.state = state;
    }


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getGender() {

        return gender;
    }


    public void setGender(String gender) {

        this.gender = gender;
    }


    public String getStudentStatus() {

        return studentStatus;
    }


    public void setStudentStatus(String studentStatus) {

        this.studentStatus = studentStatus;
    }


    public String getMajor() {

        return major;
    }


    public void setMajor(String major) {

        this.major = major;
    }


    public String getCountry() {

        return country;
    }


    public void setCountry(String country) {

        this.country = country;
    }


    public String getAge() {

        return age;
    }


    public void setAge(String age) {

        this.age = age;
    }


    public String getsAT() {

        return sAT;
    }


    public void setsAT(String sAT) {

        this.sAT = sAT;
    }


    public String getGrade() {

        return grade;
    }


    public void setGrade(String grade) {

        this.grade = grade;
    }


    public String getHeight() {

        return height;
    }


    public void setHeight(String height) {

        this.height = height;
    }
}
