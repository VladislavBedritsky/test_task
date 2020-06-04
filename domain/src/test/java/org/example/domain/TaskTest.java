package org.example.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TaskTest {

    private Task task;
    private String taskName;
    private List<String> taskPredecessors;

    @Before
    public void setDepartment() {
        this.task = new Task();
        this.taskName = "taskName";
        this.taskPredecessors = Arrays.asList("first", "second");
    }

    @Test
    public void getName() {
        task.setName("taskName");

        Assert.assertNotNull(task);
        Assert.assertEquals(taskName, task.getName());
    }

    @Test
    public void getPredecessors() {
        task.setPredecessors(Arrays.asList("first", "second"));

        Assert.assertNotNull(task);
        Assert.assertEquals(taskPredecessors.size(), task.getPredecessors().size());
    }
}