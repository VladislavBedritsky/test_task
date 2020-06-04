package org.example.domain;

import java.util.List;
import java.util.Objects;


/**
 * The task class represents a certain activities that must be done as the part of the project planning
 *
 * @version 1.01 04 Jun 2020
 * @author Uladzislau Biadrytski
 */
public class Task {

    /**
     * Unique name of the activity
     */
    private String name;

    /**
     * A list of names of the activities that must be complete in order to be able to start the current activity
     */
    private List<String> predecessors;

    public Task(String name, List<String> predecessors) {
        this.name = name;
        this.predecessors = predecessors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPredecessors() {
        return predecessors;
    }

    public void setPredecessors(List<String> predecessors) {
        this.predecessors = predecessors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(name, task.name) &&
                Objects.equals(predecessors, task.predecessors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, predecessors);
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", predecessors=" + predecessors +
                '}';
    }
}
