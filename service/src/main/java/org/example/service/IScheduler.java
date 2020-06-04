package org.example.service;

import org.example.domain.Task;

import java.util.List;

/**
 * A scheduler interface is intended to process a random list of tasks with the information of their predecessors
 * and return a list of the same tasks but in order they may be executed according to their dependencies
 *
 * @version 1.01 04 Jun 2020
 * @author Uladzislau Biadrytski
 */
public interface IScheduler {

   /**
    * Processes a random list of tasks with the information of their predecessors
    * and return a list of the same tasks but in order they may be executed according to their dependencies
    *
    * @param tasks List of random tasks
    * @return list of completed and sorted tasks
    */
   List<Task> schedule(List<Task> tasks);

}
