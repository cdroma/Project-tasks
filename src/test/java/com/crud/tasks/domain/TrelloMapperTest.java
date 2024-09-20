package com.crud.tasks.domain;

import com.crud.tasks.mapper.TrelloMapper;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TrelloMapperTest {

    private final TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    public void testMapToBoards() {
        // Given
        List<TrelloListDto> trelloListDtos = Arrays.asList(
                new TrelloListDto("1", "List 1", false),
                new TrelloListDto("2", "List 2", true)
        );
        List<TrelloBoardDto> trelloBoardDtos = Arrays.asList(
                new TrelloBoardDto("1", "Board 1", trelloListDtos)
        );

        // When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);

        // Then
        assertEquals(1, trelloBoards.size());
        assertEquals("1", trelloBoards.get(0).getId());
        assertEquals("Board 1", trelloBoards.get(0).getName());
        assertEquals(2, trelloBoards.get(0).getLists().size());
    }

    @Test
    public void testMapToBoardsDto() {
        // Given
        List<TrelloList> trelloLists = Arrays.asList(
                new TrelloList("1", "List 1", false),
                new TrelloList("2", "List 2", true)
        );
        List<TrelloBoard> trelloBoards = Arrays.asList(
                new TrelloBoard("1", "Board 1", trelloLists)
        );

        // When
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);

        // Then
        assertEquals(1, trelloBoardDtos.size());
        assertEquals("1", trelloBoardDtos.get(0).getId());
        assertEquals("Board 1", trelloBoardDtos.get(0).getName());
        assertEquals(2, trelloBoardDtos.get(0).getLists().size());
    }

    @Test
    public void testMapToList() {
        // Given
        List<TrelloListDto> trelloListDtos = Arrays.asList(
                new TrelloListDto("1", "List 1", false),
                new TrelloListDto("2", "List 2", true)
        );

        // When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);

        // Then
        assertEquals(2, trelloLists.size());
        assertEquals("1", trelloLists.get(0).getId());
        assertEquals("List 1", trelloLists.get(0).getName());
        assertFalse(trelloLists.get(0).isClosed());
        assertEquals("2", trelloLists.get(1).getId());
        assertEquals("List 2", trelloLists.get(1).getName());
        assertTrue(trelloLists.get(1).isClosed());
    }

    @Test
    public void testMapToListDto() {
        // Given
        List<TrelloList> trelloLists = Arrays.asList(
                new TrelloList("1", "List 1", false),
                new TrelloList("2", "List 2", true)
        );

        // When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);

        // Then
        assertEquals(2, trelloListDtos.size());
        assertEquals("1", trelloListDtos.get(0).getId());
        assertEquals("List 1", trelloListDtos.get(0).getName());
        assertFalse(trelloListDtos.get(0).isClosed());
        assertEquals("2", trelloListDtos.get(1).getId());
        assertEquals("List 2", trelloListDtos.get(1).getName());
        assertTrue(trelloListDtos.get(1).isClosed());
    }

    @Test
    public void testMapToCardDto() {
        // Given
        TrelloCard trelloCard = new TrelloCard("Card 1", "Description 1", "top", "1");

        // When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        // Then
        assertEquals("Card 1", trelloCardDto.getName());
        assertEquals("Description 1", trelloCardDto.getDescription());
        assertEquals("top", trelloCardDto.getPos());
        assertEquals("1", trelloCardDto.getListId());
    }

    @Test
    public void testMapToCard() {
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Card 1", "Description 1", "top", "1");

        // When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        // Then
        assertEquals("Card 1", trelloCard.getName());
        assertEquals("Description 1", trelloCard.getDescription());
        assertEquals("top", trelloCard.getPos());
        assertEquals("1", trelloCard.getListId());
    }
}

