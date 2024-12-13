package org.example.entity;

import java.util.List;

public class User {
    private String name;
    private String surname;
    private String login;
    private int age;
    private List<Integer> listPost;

    public User(String name, String surname, String login, int age, List<Integer> listPost) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.age = age;
        this.listPost = listPost;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public List<Integer> getListPost() {
        return listPost;
    }
    public void setListPost(List<Integer> listPost) {
        this.listPost = listPost;
    }

    @Override
    public String toString() {
        return "User {" + "name='" + name + ", surname='" + surname + ", login='" + login + ", age=" + age + ", listPost=" + listPost + '}';
    }
}