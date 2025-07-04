package com.practise.playground.interview;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
class Student {
    private String name;
    private Integer age;

}


public class Amex {


    public static void main(String[] args) {

        Map<String, Student> map = new HashMap<>();

        map.put("ganesh", new Student("ganesh", 27));
        map.put("raj", new Student("raj", 30));
        map.put("ram", new Student("ram", 26));

        List<Student> studentList = map.values().stream().sorted(Comparator.comparingInt(Student::getAge)).toList();

        studentList.stream().forEach(System.out::println);


    }
}
