package org.example.rest;

import org.example.domain.Task;
import org.example.service.IScheduler;
import org.example.service.impl.ISchedulerImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:test-main.xml"})
public class MainControllerTest {

    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private IScheduler iScheduler;

    private MockMvc mockMvc;
    private List<Task> tasks;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        this.tasks = Arrays.asList(
                new Task("E", Arrays.asList("B", "A", "C")),
                new Task("D", Arrays.asList("A", "B")),
                new Task("B", Arrays.asList()),
                new Task("A", Arrays.asList("B")),
                new Task("C", Arrays.asList("D", "B")),
                new Task("F", Arrays.asList("E"))
        );
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesRestDepartmentController() {
        ServletContext servletContext = wac.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("mainController"));
    }

    @Test
    public void givenSchedulerPageURI_whenMockMVC_thenReturnsDepartmentsViewName() throws Exception {

        this.mockMvc.perform(get("/scheduler"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

        IScheduler iSchedulerImpl = Mockito.mock(ISchedulerImpl.class);
        iSchedulerImpl.schedule(tasks);

        Mockito.verify(iSchedulerImpl, Mockito.times(1))
                .schedule(tasks);
    }

    @Test
    public void printCompletedAndSortedTasks() {
        for (Task task : iScheduler.schedule(tasks)) {
            System.out.println(task + "\n");
        }
    }
}