package com.practise.playground.interview;





/*
*
* /* Caesar Cipher
Text : ABC
Shift: 2
O/P: CDE

Text : ABC
Shift: 1
O/P: BCD


Text : XYZ
Shift: 1
O/P: YZA
*/

/*
 *
 * GCD: ip: 12, 8 :
 * op: 4
 *
 * */

import java.util.Comparator;
import java.util.TreeSet;

class Employee {
    Integer id;
    Integer salary;

    public Employee(Integer id, Integer salary) {
        this.id = id;
        this.salary = salary;
    }
}


public class MoneyForward {


    static String shiftChar(String s, int shift) {
        StringBuilder result = new StringBuilder();

        result.append(s.substring(shift - 1));

        int chVal = s.charAt(s.length() - 1);

        for (int i = 0; i < shift; i++) {
            chVal += 1;
            result.append((char) chVal);
        }
        return result.toString();
    }


    static int gcd(int f, int s) {
        int result = Math.min(f, s);
        for (int i = result; i > 0; i--) {
            if (s % i == 0 && f % i == 0) {
                return i;
            }
        }
        return -1;
    }

    /*
     * TreeSet. sort -> emp.salary (salary-low->high) (no duplicate)
     * */

    void treeSetQuestion() {
        Employee e4 = new Employee(4, 6000);
        Employee e5 = new Employee(2, 2000);
        Employee e6 = new Employee(10, 2000);
        Employee e1 = new Employee(1, 1000);
        Employee e2 = new Employee(2, 3000);
        Employee e3 = new Employee(3, 4000);


        TreeSet<Employee> employeeTreeSet = new TreeSet<>(Comparator.comparingInt(o1 -> o1.id));
        employeeTreeSet.add(e4);
        employeeTreeSet.add(e5);
        employeeTreeSet.add(e6);
        employeeTreeSet.add(e1);
        employeeTreeSet.add(e2);
        employeeTreeSet.add(e3);

        employeeTreeSet.stream().sorted(Comparator.comparingInt(o -> o.salary)).forEach(e -> {
            System.out.print(e.id);
            System.out.println(e.salary);
        });
    }


    public static void main(String[] args) {
        //System.out.println(MoneyForward.shiftChar("ABC", 2)); //op: CDFG
        System.out.println(gcd(12, 8));
    }
}
