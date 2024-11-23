package com.practise.lld.riderapp;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ganeshan Nagarajan
 * @since 23/11/24
 */

@Getter
@Setter
class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }
}
