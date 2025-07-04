package com.practise.java.annotation;


import java.lang.annotation.*;

/**
 * Annotation to mark fields whose actual runtime string value
 * must be one of a predefined set of names.
 */
@Target(ElementType.FIELD) // This annotation can ONLY be applied to fields
@Retention(RetentionPolicy.RUNTIME) // Keep this annotation available at runtime via reflection
@Documented
@interface NameValidator {
    // No elements needed here. The annotation's presence is the marker.
}
