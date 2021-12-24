package ru.mahalov.model;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message="Name should not be empty")
    @Size(min = 1, max = 20, message = "Size Name should between at 1 and 20 characters")
    @Column(name = "name")
    private String name;

    @Size(min = 0, max = 30, message = "Max size nick is 30 characters")
    @Column(name = "nickname")
    private String nickName;

    @NotNull(message = "Age should not be empty")
    @Min(value = 0, message="Age should between at 0 to 120")
    @Max(value = 120, message="Age should between at 0 to 120")
    @Column(name = "age")
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
