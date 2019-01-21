package com.crud.tasks.controller;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DBService;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private TaskMapper taskMapper;
    @MockBean
    private DBService dbService;

    @Test
    public void shouldGetTasks() throws Exception{
        //Given
        TaskDto taskDto = new TaskDto(1L,"task","123");
        TaskDto task2Dto = new TaskDto(2L,"task2", "234");
        TaskDto task3Dto = new TaskDto(3L,"task3", "345");
        List<TaskDto> taskDtos = new ArrayList<>();
        taskDtos.add(taskDto);
        taskDtos.add(task2Dto);
        taskDtos.add(task3Dto);
        when(taskMapper.mapToTaskDtoList(dbService.getAllTasks())).thenReturn(taskDtos);
        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$",hasSize(3)))
                .andExpect(jsonPath("$[2].title",is ("task3")))
                ;
    }
    @Test
    public void shouldGetSingleTask() throws Exception{
        //Given
        TaskDto taskDto = new TaskDto(1L,"task","123");
        when(taskMapper.mapToTaskDto(dbService.getTask(1L))).thenReturn(taskDto);
        //When & Then
        mockMvc.perform(get("/v1/task/getTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.title",is("task")))
                .andExpect(jsonPath("$.content",is("123")))
                .andExpect(jsonPath("$.id",is(1)));
    }

    @Test
    public void shouldUpdateTask()  throws Exception{
        //Given
        TaskDto taskDto = new TaskDto(1L,"task23","123");
        Task task = taskMapper.mapToTask(taskDto);
        when(taskMapper.mapToTaskDto(dbService.saveTask(task))).thenReturn(taskDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(put("/v1/task/updateTask").contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.title",is("task23")))
                .andExpect(jsonPath("$.content",is("123")))
                .andExpect(jsonPath("$.id",is(1)));
    }
    @Test
    public void shouldDeleteTask() throws Exception{
        //Given
        TaskDto taskDto = new TaskDto(1L,"task","123");
        //When & Then
        mockMvc.perform(delete("/v1/task/deleteTask").param("taskId","1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateTask() throws Exception{
        //Given
        TaskDto taskDto = new TaskDto(1L,"task","123");
        Task task = taskMapper.mapToTask(taskDto);
        when(taskMapper.mapToTaskDto(dbService.saveTask(anyObject()))).thenReturn(taskDto);
        when(dbService.saveTask(anyObject())).thenReturn(new Task(1L, "task", "123"));
        when (taskMapper.mapToTask(anyObject())).thenReturn(new Task(1L, "task", "123"));
        when(taskMapper.mapToTaskDto(anyObject())).thenReturn(taskDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When & Then
        mockMvc.perform(post("/v1/task/createTask").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title",is("task")))
                .andExpect(jsonPath("$.content",is("123")))
                .andExpect(jsonPath("$.id",is(1)));
    }


}