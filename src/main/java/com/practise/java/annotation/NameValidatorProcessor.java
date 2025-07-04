package com.practise.java.annotation;


import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

class NameValidatorProcessor {

    // Define the list of allowed string values for fields marked with @NameValidator
    private static final List<String> ALLOWED_NAMES = Arrays.asList("GANESHAN", "NAGARAJAN");

    /**
     * Validates the actual runtime values of all fields in a given object instance
     * that are annotated with @NameValidator.
     * Reports whether the field's value is one of the allowed names.
     *
     * @param instance The object instance to inspect and validate.
     * @return true if all annotated fields have valid names, false otherwise.
     */
    public static boolean validateObject(Object instance) {
        System.out.println("--- Validating object instance of class: " + instance.getClass().getName() + " ---");

        Class<?> targetClass = instance.getClass();
        Field[] fields = targetClass.getDeclaredFields(); // Get all fields (public, private, etc.)

        boolean allValid = true; // Assume valid until a failure is found

        for (Field field : fields) {
            // Check if the current field is marked with our @NameValidator annotation
            if (field.isAnnotationPresent(NameValidator.class)) {
                System.out.println("  Field '" + field.getName() + "' is marked with @NameValidator.");

                // --- Now, get the actual runtime value of this field from the instance ---
                try {
                    // Make the field accessible in case it's private or protected
                    field.setAccessible(true);

                    // Get the value of the field for the specific instance
                    Object fieldValueObject = field.get(instance);

                    // We expect String fields, but let's be safe
                    if (!(fieldValueObject instanceof String)) {
                        System.err.println("    Validation: FAILED (Field is annotated but is not a String type. Value: " + fieldValueObject + ")");
                        allValid = false;
                        continue; // Move to the next field
                    }

                    String fieldValue = (String) fieldValueObject;
                    System.out.println("    Actual field value: \"" + fieldValue + "\"");


                    // Check if the obtained field value is in our list of allowed names
                    if (ALLOWED_NAMES.contains(fieldValue)) {
                        System.out.println("    Validation: PASSED (Value '" + fieldValue + "' is allowed)");
                    } else {
                        System.err.println("    Validation: FAILED (Value '" + fieldValue + "' is NOT allowed. Must be one of: " + ALLOWED_NAMES + ")");
                        allValid = false; // Mark that at least one validation failed
                    }

                } catch (IllegalAccessException e) {
                    // This shouldn't happen with setAccessible(true), but good practice to catch
                    System.err.println("    Error accessing field '" + field.getName() + "': " + e.getMessage());
                    e.printStackTrace();
                    allValid = false; // Consider access failure as validation failure
                }
            } else {
                // Optional: Report on fields that are not annotated
                // System.out.println("  Field '" + field.getName() + "' is not marked with @NameValidator - Skipping validation.");
            }
        }

        System.out.println("--- Validation finished ---");

        // You might want to throw an exception here if validationFailed is true
        if (!allValid) {
            System.err.println("Annotation-based field validation failed for object instance of " + targetClass.getName());
            // throw new RuntimeException("Annotation-based field validation failed for object instance of " + targetClass.getName());
        }

        return allValid; // Return the validation result
    }

    public static void main(String[] args) {
        // Create an instance of PersonData with values
        PersonData person1 = new PersonData("GANESHAN", "NAGARAJAN", "JOHN", "SMITH", 30);
        PersonData person2 = new PersonData("GANESHAN", "SomeOtherName", "NAGARAJAN", "AnotherName", 25);
        PersonData person3 = new PersonData("ValidFirstName", "ValidLastName", "GANESHAN", "NAGARAJAN", 40); // Note: ValidFirstName/LastName are not in the allowed list, but they are NOT ANNOTATED, so they pass implicitly by being ignored.

        // Run the validator on the instances
        System.out.println("Validating person1:");
        validateObject(person1);

        System.out.println("\nValidating person2:");
        validateObject(person2);

        System.out.println("\nValidating person3:");
        validateObject(person3);
    }
}
