package com.practise.playground.interview;


import java.util.Comparator;
import java.util.List;

record Employees(String name, Integer salary) {
}


/*
 *
 *
 *
 * */


public class AmericanExpress {


    /*
     * 3rd highest
     * */
    public Employees getEmployeeDetails(List<Employees> employees) {
        employees.sort(Comparator.comparing(Employees::salary));
        employees = employees.reversed();
        for (Employees e : employees) {
            System.out.println(e.name());
        }
        System.out.println();
        return employees.get(2);
    }


    /**/

    public void p2(int[] arr) {

        int[] result = new int[arr.length];

        int count = 0;
        for (int a : arr) {
            if (a != 5) {
                result[count++] = a;
            }
        }

        for (int r : result) {
            System.out.println(r);
        }
    }





    public static void main(String[] args) {

        AmericanExpress am = new AmericanExpress();

//        List<Employees> employeeList = new ArrayList<>();
//        Employees e1 = new Employees("ganesh", 1000);
//        Employees e2 = new Employees("nagarajan", 200);
//        Employees e3 = new Employees("raj", 500);
//        employeeList.add(e1);
//        employeeList.add(e2);
//        employeeList.add(e3);
//
//
//        AmericanExpress am = new AmericanExpress();
//
//        System.out.println(am.getEmployeeDetails(employeeList).name());

        am.p2(new int[]{6, 0, 4, 1, 0, 7, 0, 0, 9});


    }
}
