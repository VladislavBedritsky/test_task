package org.example.service.impl;

import org.example.domain.Task;
import org.example.service.TaskService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:test.xml"})
public class TaskServiceImplTest {

    @Autowired
    private TaskService taskService;

    private List<Task> tasksList;
    private List<Task> completedTasks;
    private List<String> predecessors;

    @Before
    public void setUp() {
        this.tasksList = Arrays.asList(
                new Task("E", Arrays.asList("B", "A")),
                new Task("D", Arrays.asList("A", "B")),
                new Task("A", Arrays.asList()),
                new Task("B", Arrays.asList("A")),
                new Task("C", Arrays.asList("D", "B")),
                new Task("F", Arrays.asList("E"))
        );
        this.completedTasks = Arrays.asList(
                new Task("A", Arrays.asList()),
                new Task("B", Arrays.asList("A"))
        );
        this.predecessors = Arrays.asList(
                "A","B"
        );
    }

    @Test
    public void findTaskWhichStartsFirst() {
        Task task = taskService.findTaskWhichStartsFirst(tasksList);

        Assert.assertNotNull(task);
        Assert.assertEquals("A", task.getName());
    }

    @Test
    public void checkIfPredecessorsEqualsToCompletedTasks() {
        Boolean result = taskService.checkIfPredecessorsEqualsToCompletedTasks(completedTasks, predecessors);

        Assert.assertNotNull(result);
        Assert.assertTrue(result);
    }

    @Test
    public void checkIfTaskAlreadyExistsInCompletedTasks() {
        Task task = new Task("B", Arrays.asList("A"));
        Boolean result = taskService.checkIfTaskAlreadyExistsInCompletedTasks(completedTasks, task);

        Assert.assertNotNull(result);
        Assert.assertTrue(result);
    }

    @Test
    public void completeTasks() {
        TaskService taskService = Mockito.mock(TaskServiceImpl.class);
        Mockito.doNothing().when(taskService).completeTasks(isA(List.class), isA(List.class));
        taskService.completeTasks(tasksList, completedTasks);

        Mockito.verify(taskService, Mockito.times(1))
                .completeTasks(tasksList, completedTasks);
    }
}