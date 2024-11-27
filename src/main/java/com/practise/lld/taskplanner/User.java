package com.practise.lld.taskplanner;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ganeshan Nagarajan
 * @since 27/11/24
 */


@NoArgsConstructor
@Getter
@Setter
class User {
    private String name;
    private List<Task> taskList;
    private List<Sprint> sprintList;

    public User(String name, List<Task> taskList, List<Sprint> sprintList) {
        this.name = name;
        this.taskList = taskList;
        this.sprintList = sprintList;
    }

    public User(String name) {
        this.name = name;
        this.taskList = new ArrayList<>();
        this.sprintList = new ArrayList<>();
    }

    void createTask(String name, String subtract, TaskType type) {
        taskList.add(new Task(name, subtract, type, TaskStatus.NEW, this));
    }

    void createSprint(String name, int start, int end) {
        sprintList.add(new Sprint(name, start, end));
    }

    void addToSprint() {

    }

    void removeFromSprint() {

    }

    void changeTaskStatus() {

    }

}
