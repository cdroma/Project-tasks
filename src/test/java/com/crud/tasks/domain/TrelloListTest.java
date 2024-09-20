package com.crud.tasks.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrelloListTest {

    @Test
    void shouldCreateTrelloListWithGivenParameters() {
        // Arrange
        String expectedId = "1";
        String expectedName = "List Name";
        boolean expectedIsClosed = false;

        // Act
        TrelloList trelloList = new TrelloList(expectedId, expectedName, expectedIsClosed);

        // Assert
        assertEquals(expectedId, trelloList.getId());
        assertEquals(expectedName, trelloList.getName());
        assertEquals(expectedIsClosed, trelloList.isClosed());
    }

    @Test
    void shouldCreateEmptyTrelloList() {
        // Act
        TrelloList trelloList = new TrelloList();

        // Assert
        assertEquals(null, trelloList.getId());
        assertEquals(null, trelloList.getName());
        assertEquals(false, trelloList.isClosed());
    }
}