package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

public class TrelloValidatorTest {

    private TrelloValidator trelloValidator;

    @BeforeEach
    void setUp() {
        trelloValidator = new TrelloValidator();
    }

    @Test
    void shouldLogMessageWhenCardContainsTest() {
        // Arrange
        TrelloCard testCard = new TrelloCard("test card", "description", "top", "1");

        // Act
        trelloValidator.validateCard(testCard);

    }

    @Test
    void shouldFilterOutTestBoards() {
        // Arrange
        TrelloBoard board1 = new TrelloBoard("1", "test");
        TrelloBoard board2 = new TrelloBoard("2", "production");
        List<TrelloBoard> boards = Arrays.asList(board1, board2);

        // Act
        List<TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(boards);

        // Assert
        assert(filteredBoards.size() == 1);
        assert(filteredBoards.get(0).getName().equals("production"));
    }

    @Test
    void shouldReturnEmptyListWhenAllBoardsAreTest() {
        // Arrange
        TrelloBoard board1 = new TrelloBoard("1", "test");
        TrelloBoard board2 = new TrelloBoard("2", "test");
        List<TrelloBoard> boards = Arrays.asList(board1, board2);

        // Act
        List<TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(boards);

        // Assert
        assert(filteredBoards.isEmpty());
    }
}