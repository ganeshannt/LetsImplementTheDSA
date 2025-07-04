package com.practise.playground.interview;


import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
class Person {
    Integer id;
    String name;
    Double salary;
    Department department;
}

@AllArgsConstructor
class Department {
    Integer id;
    String department;
}


public class Mphasis {

    public static void main(String[] args) {
        List<Person> persons = List.of(
                new Person(1, "Alex", 100d, new Department(1, "HR")),
                new Person(2, "Brian", 200d, new Department(1, "HR")),
                new Person(3, "Charles", 900d, new Department(2, "Finance")),
                new Person(4, "David", 200d, new Department(2, "Finance")),
                new Person(5, "Edward", 200d, new Department(2, "Finance")),
                new Person(6, "Frank", 800d, new Department(3, "ADMIN")),
                new Person(7, "George", 900d, new Department(3, "ADMIN")));

        persons.stream().collect(Collectors.groupingBy(e -> e.department.department)).forEach((k, v) -> {
            Double sum = v.stream().mapToDouble(e -> e.salary).sum();
            System.out.printf("Department: %s , SumOfDepartmentSalary: %2f%n", k, sum);
        });
    }
}
