package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TaskMapperTest {

    private TaskMapper taskMapper;

    @BeforeEach
    void setUp() {
        taskMapper = new TaskMapper();
    }

    @Test
    void shouldMapToTask() {
        // Given
        TaskDto taskDto = new TaskDto(1L, "Test Title", "Test Content");

        // When
        Task task = taskMapper.mapToTask(taskDto);

        // Then
        assertThat(task).isNotNull();
        assertThat(task.getId()).isEqualTo(taskDto.getId());
        assertThat(task.getTitle()).isEqualTo(taskDto.getTitle());
        assertThat(task.getContent()).isEqualTo(taskDto.getContent());
    }

    @Test
    void shouldMapToTaskDto() {
        // Given
        Task task = new Task(1L, "Test Title", "Test Content");

        // When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        // Then
        assertThat(taskDto).isNotNull();
        assertThat(taskDto.getId()).isEqualTo(task.getId());
        assertThat(taskDto.getTitle()).isEqualTo(task.getTitle());
        assertThat(taskDto.getContent()).isEqualTo(task.getContent());
    }

    @Test
    void shouldMapToTaskDtoList() {
        // Given
        Task task1 = new Task(1L, "Title 1", "Content 1");
        Task task2 = new Task(2L, "Title 2", "Content 2");
        List<Task> taskList = List.of(task1, task2);

        // When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);

        // Then
        assertThat(taskDtoList).isNotNull();
        assertThat(taskDtoList).hasSize(2);
        assertThat(taskDtoList.get(0).getId()).isEqualTo(task1.getId());
        assertThat(taskDtoList.get(1).getId()).isEqualTo(task2.getId());
    }

    @Test
    void shouldReturnEmptyListWhenMappingEmptyTaskList() {
        // Given
        List<Task> emptyTaskList = Collections.emptyList();

        // When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(emptyTaskList);

        // Then
        assertThat(taskDtoList).isNotNull();
        assertThat(taskDtoList).isEmpty();
    }
}