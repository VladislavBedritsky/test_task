package org.example.rest;

import org.example.domain.Task;
import org.example.service.IScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Main REST controller
 *
 * @version 1.01 04 Jun 2020
 * @author Uladzislau Biadrytski
 */
@RestController
@RequestMapping(path = "/scheduler", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
public class MainController {

    @Autowired
    private IScheduler scheduler;

    private List<Task> tasks;

    public MainController() {
        this.tasks = Arrays.asList(
                new Task("E", Arrays.asList("B", "A", "C")),
                new Task("D", Arrays.asList("A", "B")),
                new Task("A", Arrays.asList()),
                new Task("B", Arrays.asList("A")),
                new Task("C", Arrays.asList("D", "B")),
                new Task("F", Arrays.asList("E"))
        );
    }

    /**
     * GET request("/scheduler") for all tasks which have been completed and sorted.
     *
     * @return list of tasks
     */
    @GetMapping
    private List<Task> getCompletedAndSortedTasks() {
        return scheduler.schedule(tasks);
    }

}
