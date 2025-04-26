package com.practise.java.cloning;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
class Student implements Cloneable {

    private String name;
    private int age;
    private int[] marks;
    private Course course;


    // Shallow copy of the object. Primitive types are copied because they will be stored in the stack.
    // Reference types are copied as reference not the actual object created as a new object.
    // Uncomment the following method to use shallow copy.
    // @Override
    // protected Object clone() throws CloneNotSupportedException {
    //     return super.clone();
    // }

    // Deep copy of the object. Primitive types are copied and reference types are copied as new objects.
    @Override
    protected Object clone() throws CloneNotSupportedException {
        // Call the clone method of the superclass (Object) to create a shallow copy.
        Student student = (Student) super.clone();
        // Perform deep copy for the reference type field 'marks'.
        // Clone the array to create a new array object with the same content.
        // if it is a primitive type array, you can use the clone method directly.
        // if it is a reference type array, you need to create a new array and copy the content of the original array.
        student.marks = this.marks.clone(); //creates a new array with the same contents as the original marks array, ensuring that the mark field is deeply copied.

        // since the course field is a reference type, creates a new Course object with the same courseName as the original course object, ensuring that the course field is deeply copied.
        student.course = new Course(this.course.getCourseName());

        return student;
    }
}