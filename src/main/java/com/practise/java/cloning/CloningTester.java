package com.practise.java.cloning;

class CloningTester {

    public static void main(String[] args) {
        // Create an instance, of Course.
        Course course = new Course("Mathematics");
        // Create an instance of Student with name, age, marks, and course.
        Student student = new Student("Ganeshan", 25, new int[]{90, 80, 70}, course);
        System.out.println("Original Student: " + student);
        try {
            // Clone the student object. This will call the overridden clone method in Student class.
            Student clonedStudent = (Student) student.clone();
            System.out.println("Cloned Student: " + clonedStudent);

            // we implemented clone() method to perform deep copy, so the memory address is different.
            System.out.println("Original Student == Cloned Student: " + (student == clonedStudent)); // false

            // Check if the original and cloned student objects are equal.
            // This should print true if the equals method is properly overridden.
            System.out.println("Original Student.equals(Cloned Student): " + student.equals(clonedStudent));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}