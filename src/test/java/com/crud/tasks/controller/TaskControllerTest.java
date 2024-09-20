package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class TaskControllerTest {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private DbService dbService;

    @Mock
    private TaskMapper taskMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetTasks() {
        // Given
        Task task = new Task(1L, "Test Task", "Test Content");
        TaskDto taskDto = new TaskDto(1L, "Test Task", "Test Content");
        List<Task> tasks = Arrays.asList(task);
        List<TaskDto> taskDtos = Arrays.asList(taskDto);

        when(dbService.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(taskDtos);

        // When
        ResponseEntity<List<TaskDto>> response = taskController.getTasks();

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(taskDtos);
    }

    @Test
    void shouldGetTask() throws TaskNotFoundException {
        // Given
        Long taskId = 1L;
        Task task = new Task(taskId, "Test Task", "Test Content");
        TaskDto taskDto = new TaskDto(taskId, "Test Task", "Test Content");

        when(dbService.getTask(taskId)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        // When
        ResponseEntity<TaskDto> response = taskController.getTask(taskId);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(taskDto);
    }

    @Test
    void shouldDeleteTask() {
        // Given
        Long taskId = 1L;

        // When
        ResponseEntity<Void> response = taskController.deleteTask(taskId);

        // Then
        verify(dbService, times(1)).deleteTask(taskId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldUpdateTask() {
        // Given
        TaskDto taskDto = new TaskDto(1L, "Updated Task", "Updated Content");
        Task task = new Task(1L, "Updated Task", "Updated Content");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        // When
        ResponseEntity<TaskDto> response = taskController.updateTask(taskDto);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(taskDto);
    }

    @Test
    void shouldCreateTask() {
        // Given
        TaskDto taskDto = new TaskDto(null, "New Task", "New Content");
        Task task = new Task(null, "New Task", "New Content");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);

        // When
        ResponseEntity<Void> response = taskController.createTask(taskDto);

        // Then
        verify(dbService, times(1)).saveTask(task);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
