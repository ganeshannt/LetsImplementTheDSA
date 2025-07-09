package com.practise.java.lambda.streams;

import com.practise.commons.Utils;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * find the students with the highest marks in different subjects and each class
 */

@AllArgsConstructor
class Student {
    String name;
    String className;
    List<Score> scores;
}

@AllArgsConstructor
class Score {
    String subject;
    int marks;
}

public class StreamsObjProblem2 {

    static List<String> findStudentNameWithHighestMarkInDiffSub(List<Student> studentList) {
        return studentList.stream()
                .flatMap(student ->
                        student.scores.stream()
                                .map(score ->
                                        Map.entry(score.subject,
                                                Map.entry(student.name, score.marks))))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1.getValue() >= e2.getValue() ? e1 : e2))
                .values().stream()
                .map(Map.Entry::getKey)
                .toList();
    }


    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("Mercury", "10A", List.of(new Score("Math", 92), new Score("Science", 88))),
                new Student("Venus", "10A", List.of(new Score("Math", 95), new Score("Science", 91))),
                new Student("Mars", "10B", List.of(new Score("Math", 89), new Score("Science", 85))),
                new Student("Uranus", "10B", List.of(new Score("Math", 97), new Score("Science", 90)))
        );
        Utils.printList(findStudentNameWithHighestMarkInDiffSub(students));
    }
}
