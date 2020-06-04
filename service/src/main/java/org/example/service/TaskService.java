package org.example.service;

import org.example.domain.Task;

import java.util.List;

/**
 * Task Service
 *
 * @version 1.01 04 Jun 2020
 * @author Uladzislau Biadrytski
 */
public interface TaskService {

    /**
     * Find task which will complete first.
     *
     * @param tasks Data tasks
     * @return Task
     */
    Task findTaskWhichStartsFirst(List<Task> tasks);

    /**
     * Check if task predecessors are equals to completed tasks
     *
     * @param completedTasks Tasks which are completed
     * @param predecessors Task predecessors
     * @return Boolean
     */
    Boolean checkIfPredecessorsEqualsToCompletedTasks (
            List<Task> completedTasks, List<String> predecessors );

    /**
     * Check if task is already exists in completed tasks
     *
     * @param completedTasks Tasks which are completed
     * @param task Data task
     * @return Boolean
     */
    Boolean checkIfTaskAlreadyExistsInCompletedTasks (
            List<Task> completedTasks, Task task);

    /**
     * An algorithm that checks whether tasks can be completed
     * and then add them to collection of completed tasks. If task predecessors
     * will be set in wrong order method will throw an exception.
     *
     * @param tasks Data tasks
     * @param completedTasks Completed tasks
     */
    void completeTasks (List<Task> tasks, List<Task> completedTasks);
}
