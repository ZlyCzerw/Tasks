package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TaskMapperTestSuite {

    @Autowired
    private TaskMapper taskMapper;


    @Test
    public void mapToTaskDto(){
        //Given
       Task task = new Task(12L,"task1","content");
       TaskDto taskDto = new TaskDto(1L,"taskDto1","text");
        //When
        TaskDto maddpedToDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals("content",maddpedToDto.getContent());
    }
    @Test
    public void mapToTask(){
        //Given
        Task task = new Task(12L,"task1","content");
        TaskDto taskDto = new TaskDto(1L,"taskDto1","text");
        //When
        Task mappedToTask = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals("taskDto1", mappedToTask.getTitle());
    }
    @Test
    public void shouldReturnTaskDtoList(){
        //given
        Task task = new Task(12L,"task1","content");
        TaskDto taskDto = new TaskDto(1L,"taskDto1","text");
        List<Task>tasks = new ArrayList<>();
        tasks.add(task);
        //when
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(tasks);
        //then
        assertEquals(1, taskDtoList.size());
    }

}