package com.jdv.ulysse.myapplication;

/**
 * Created by ulysse on 05/10/2017.
 */

public class Client {

    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String gender;
    private String level;
    private Boolean activ;

    /**
     *
     * @param firstName
     * @param lastName
     * @param age
     * @param email
     * @param gender
     * @param level
     * @param activ
     */
    public Client(String firstName, String lastName, int age, String email, String gender, String level, Boolean activ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.gender = gender;
        this.level = level;
        this.activ = activ;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Boolean getActiv() {
        return activ;
    }

    public void setActiv(Boolean activ) {
        this.activ = activ;
    }
}
