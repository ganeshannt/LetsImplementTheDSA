package com.practise.lld.taskplanner;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Ganeshan Nagarajan
 * @since 27/11/24
 */

@Setter
@Getter
@NoArgsConstructor
class Task {
    private static int counter = 0;
    private int id;
    private String name;
    private String subtract;
    private TaskType type;
    private TaskStatus status;
    private User user;


    public Task(String name, String subtract, TaskType type, TaskStatus status, User user) {
        this.id = getUniqueId();
        this.name = name;
        this.subtract = subtract;
        this.type = type;
        this.status = status;
        this.user = user;
    }


    static int getUniqueId() {
        counter = counter + 1;
        return counter;
    }
}
