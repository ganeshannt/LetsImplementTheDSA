package com.practise.lld.taskplanner;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ganeshan Nagarajan
 * @since 27/11/24
 */
@Getter
@Setter
class Sprint {
    private String name;
    private int start;
    private int end;
    private List<Task> taskList;


    public Sprint(String name, int start, int end, List<Task> taskList) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.taskList = taskList;
    }

    public Sprint(String name, int start, int end) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.taskList = new ArrayList<>();
    }

    void addTask(Task task) {
        this.taskList.add(task);
    }
}
