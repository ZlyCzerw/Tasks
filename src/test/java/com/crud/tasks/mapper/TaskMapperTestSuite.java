package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TaskMapperTestSuite {

    private TaskMapper taskMapper = new TaskMapper();

    private Task task = new Task(12L,"task1","content");
    private TaskDto taskDto = new TaskDto(1L,"taskDto1","text");

    @Test
    public void mapToTaskDto(){
        //Given

        //When
        TaskDto maddpedToDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals("content",maddpedToDto.getContent());
    }
    @Test
    public void mapToTask(){
        //Given

        //When
        Task mappedToTask = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals("taskDto1", mappedToTask.getTitle());
    }
    @Test
    public void shouldReturnTaskDtoList(){
        //given
        List<Task>tasks = new ArrayList<>();
        tasks.add(task);
        //when
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(tasks);
        //then
        assertEquals(1, taskDtoList.size());
    }

}