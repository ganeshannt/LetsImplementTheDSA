// File: PersonData.java
package com.practise.java.annotation;


import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
 class PersonData {

    @NameValidator // Mark this field for validation
    private String firstName;

    @NameValidator // Mark this field for validation
    private String lastName;

    @NameValidator // Mark this field for validation (will be invalid value)
    private String middleName;

    @NameValidator // Mark this field for validation (will be invalid value)
    private String familyName;

    private int age; // Not annotated, won't be validated by our processor
}
