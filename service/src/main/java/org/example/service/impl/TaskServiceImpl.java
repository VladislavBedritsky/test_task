package org.example.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.Task;
import org.example.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TaskService interface implementation
 *
 * @version 1.01 04 Jun 2020
 * @author Uladzislau Biadrytski
 */
@Service
public class TaskServiceImpl implements TaskService {

    private static Logger LOGGER = LogManager.getLogger(TaskServiceImpl.class);

    @Override
    public Task findTaskWhichStartsFirst(List<Task> tasks) {
        for (Task task : tasks) {
            if (task.getPredecessors().size() == 0) {
                return task;
            }
        }
        return new Task();
    }

    @Override
    public Boolean checkIfPredecessorsEqualsToCompletedTasks (
            List<Task> completedTasks, List<String> predecessors ) {

        int coincidencesCounter = 0;

        for (Task task : completedTasks) {
            for (String predecessor : predecessors) {
                if (predecessor.equals(task.getName())) {
                    coincidencesCounter++;
                }
                if (coincidencesCounter == predecessors.size()) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public Boolean checkIfTaskAlreadyExistsInCompletedTasks (
            List<Task> completedTasks, Task task) {

        for (Task temp : completedTasks) {
            if (temp.getName().equals(task.getName())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void completeTasks (List<Task> tasks, List<Task> completedTasks) {
        int iterateCounter = 0;

        outer:
        do {

            for (Task task : tasks) {

                Boolean isPredecessorsEqualsToCompletedTasks =
                        checkIfPredecessorsEqualsToCompletedTasks(completedTasks, task.getPredecessors());
                Boolean isTaskAlreadyExistsInCompletedTasks =
                        checkIfTaskAlreadyExistsInCompletedTasks(completedTasks, task);

                if(isPredecessorsEqualsToCompletedTasks &&
                        !isTaskAlreadyExistsInCompletedTasks) {
                    completedTasks.add(task);
                }
            }

            iterateCounter++;
            if (iterateCounter > tasks.size()) {
                try {
                    throw new RuntimeException();
                } catch (RuntimeException e) {
                    LOGGER.error("Predecessors wrong order. " +
                            "Not all methods can be completed!");
                }
                break outer;
            }
        } while (tasks.size() != completedTasks.size());
    }


}
