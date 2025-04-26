package com.practise.java.collection;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@ToString

/*
 * How Comparable interface works?
 * 1. If we want to sort the objects based on some natural order, then we should go for a Comparable interface.
 * 2. compareTo() method is present in a Comparable interface, and based on its implementation-only sorting will be done.
 * 3. If Comparable and Comparator both are available, then precedence will be given to Comparator only.
 * */

public class Student implements Comparable<Student> {
    private int maths;
    private int physics;

    @Override
    public int compareTo(Student o) {
        /*

         1. if compareTo() method returns -ve value, then the current object will come before other object.
         2. if compareTo() method returns +ve value, then the current object will come after other object.
         3. if compareTo() method returns 0 then both objects are equal.
         4. Default sorting order is ascending order.

            Current object < other object
                return -ve
            current object > other object
                return +ve
            current object == other object
                return 0

            Below code converted like this during runtime (ascending order):
            if (this.getMaths() < o.getMaths()) {
                return -1;
            } else if (this.getMaths() > o.getMaths()) {
                return 1;
            } else {
                return 0;
            }

            Ascending order: return this.getMaths() - o.getMaths();
            Descending order: return o.getMaths() - this.getMaths();

            Ascending order: numbers.sort((a, b) -> a - b);
            Descending order: numbers.sort((a, b) -> b - a);

            List<Integer> numbers = Arrays.asList(1,2,3,6,5);
            numbers.sort((a, b) -> a - b);
            numbers.forEach(System.out::println); \\12356
            numbers.sort((a,b)->b-a);
            numbers.forEach(System.out::println); \\ 65321

         */

        System.out.println("Comparable interface compareTo() method called");


        return this.getMaths() - o.getMaths();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return getMaths() == student.getMaths() && getPhysics() == student.getPhysics();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMaths(), getPhysics());
    }
}