package org.example.service.impl;

import org.example.domain.Task;
import org.example.service.IScheduler;
import org.example.service.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations={"classpath*:test.xml"})
public class ISchedulerImplTest {

    @InjectMocks
    private IScheduler iScheduler = new ISchedulerImpl();
    @Mock
    private TaskService taskService;

    @Test
    public void schedule() {
        iScheduler.schedule(Collections.singletonList(new Task()));
        Mockito.verify(taskService, Mockito.times(1))
                .findTaskWhichStartsFirst(isA(List.class));
        Mockito.verify(taskService, Mockito.times(1))
                .completeTasks(isA(List.class),isA(List.class));
    }
}