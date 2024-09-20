package com.crud.tasks.controller;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TaskNotFoundExceptionTest {

    @Test
    void shouldInstantiateTaskNotFoundException() {
        // Given
        String message = "Task not found";

        // When
        TaskNotFoundException exception = new TaskNotFoundException();

        // Then
        assertThat(exception).isNotNull();
        assertThat(exception).isInstanceOf(TaskNotFoundException.class);
    }

    @Test
    void shouldHaveDefaultMessageWhenThrown() {
        // Given
        Exception exception = new TaskNotFoundException();

        // When
        String message = exception.getMessage();

        // Then
        assertThat(message).isNull();
    }
}