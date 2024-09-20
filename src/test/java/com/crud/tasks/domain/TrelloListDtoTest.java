package com.crud.tasks.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrelloListDtoTest {

    @Test
    void shouldCreateTrelloListDtoWithGivenParameters() {
        // Arrange
        String expectedId = "1";
        String expectedName = "List Name";
        boolean expectedIsClosed = false;

        // Act
        TrelloListDto trelloListDto = new TrelloListDto(expectedId, expectedName, expectedIsClosed);

        // Assert
        assertEquals(expectedId, trelloListDto.getId());
        assertEquals(expectedName, trelloListDto.getName());
        assertEquals(expectedIsClosed, trelloListDto.isClosed());
    }

    @Test
    void shouldCreateEmptyTrelloListDto() {
        // Act
        TrelloListDto trelloListDto = new TrelloListDto();

        // Assert
        assertEquals(null, trelloListDto.getId());
        assertEquals(null, trelloListDto.getName());
        assertEquals(false, trelloListDto.isClosed());
    }
}