package org.example.service.impl;

import org.example.domain.Task;
import org.example.service.IScheduler;
import org.example.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * IScheduler interface implementation
 *
 * @version 1.01 04 Jun 2020
 * @author Uladzislau Biadrytski
 */

@Service
public class ISchedulerImpl implements IScheduler {

    @Autowired
    private TaskService taskService;

    @Override
    public List<Task> schedule(List<Task> tasks) {
        taskService.qq();
        return new ArrayList<>();
    }
}
