package org.example.service.impl;

import org.example.service.TaskService;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Override
    public void qq() {
        System.out.println("qq");
    }
}
