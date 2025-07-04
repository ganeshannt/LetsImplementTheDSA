package com.practise.playground.interview;


/*
*
* List<Employee>
 different department
 * highest paid emp each department
* */


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
class Empl {
    String name;
    String department;
    Integer salary;
}

public class Altimetric {

    public static void main(String[] args) {

        Empl e1 = new Empl("ram", "cse", 100);
        Empl e2 = new Empl("rahu", "cse", 20);
        Empl e3 = new Empl("raj", "eee", 80);
        Empl e4 = new Empl("mani", "eee", 23);

        List<Empl> emplList = new ArrayList<>();

        emplList.add(e1);
        emplList.add(e2);
        emplList.add(e3);
        emplList.add(e4);

        List<Optional<Empl>> highestPaid = emplList.stream()
                .collect(Collectors.groupingBy(e -> e.department,
                        Collectors.maxBy(Comparator.comparingInt(Empl::getSalary))))
                .values()
                .stream()
                .toList();

        for (Optional<Empl> optional : highestPaid) {
            if (optional.isPresent()) {
                Empl empl = optional.get();
                System.out.println(empl.name);
                System.out.println(empl.department);
                System.out.println(empl.salary);
                System.out.println("-----------------------------");
            }
        }

    }
}
