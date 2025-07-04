package com.practise.commons;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {
    private String name;
    private int age;
    private String department;
    private double salary;
}
