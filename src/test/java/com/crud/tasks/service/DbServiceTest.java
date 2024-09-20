package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetAllTasks() {
        // Given
        Task task1 = new Task(1L, "Task 1", "Content 1");
        Task task2 = new Task(2L, "Task 2", "Content 2");
        when(repository.findAll()).thenReturn(Arrays.asList(task1, task2));

        // When
        List<Task> tasks = dbService.getAllTasks();

        // Then
        assertThat(tasks).hasSize(2);
        assertThat(tasks).containsExactlyInAnyOrder(task1, task2);
    }

    @Test
    void shouldSaveTask() {
        // Given
        Task task = new Task(1L, "Task", "Content");
        when(repository.save(task)).thenReturn(task);

        // When
        Task savedTask = dbService.saveTask(task);

        // Then
        assertThat(savedTask).isEqualTo(task);
        verify(repository, times(1)).save(task);
    }

    @Test
    void shouldGetTaskById() throws TaskNotFoundException {
        // Given
        Long taskId = 1L;
        Task task = new Task(taskId, "Task", "Content");
        when(repository.findById(taskId)).thenReturn(Optional.of(task));

        // When
        Task foundTask = dbService.getTask(taskId);

        // Then
        assertThat(foundTask).isEqualTo(task);
    }

    @Test
    void shouldThrowExceptionWhenTaskNotFound() {
        // Given
        Long taskId = 1L;
        when(repository.findById(taskId)).thenReturn(Optional.empty());

        // When & Then
        try {
            dbService.getTask(taskId);
        } catch (TaskNotFoundException e) {
            assertThat(e).isNotNull();
        }
    }

    @Test
    void shouldDeleteTask() {
        // Given
        Long taskId = 1L;

        // When
        dbService.deleteTask(taskId);

        // Then
        verify(repository, times(1)).deleteById(taskId);
    }
}
