package ru.hogwarts.school.model;

import nonapi.io.github.classgraph.json.Id;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;


@Entity
public class Student {

    @Id
    @GeneratedValue
    Long id;
    String name;
    String color;

    public Student(Long id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
