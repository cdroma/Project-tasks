package com.crud.tasks.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreatedTrelloCardDtoTest {

    @Test
    void shouldCreateCreatedTrelloCardDtoWithGivenParameters() {
        // Arrange
        String expectedId = "1";
        String expectedName = "Test Card";
        String expectedShortUrl = "http://example.com";
        CreatedTrelloCardDto.Badges badges = new CreatedTrelloCardDto.Badges();
        badges.setVotes(5);
        badges.setAttachmentsByType(new CreatedTrelloCardDto.AttachmentsByType());

        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto(expectedId, expectedName, expectedShortUrl);
        createdTrelloCardDto.setBadges(badges);

        // Act & Assert
        assertEquals(expectedId, createdTrelloCardDto.getId());
        assertEquals(expectedName, createdTrelloCardDto.getName());
        assertEquals(expectedShortUrl, createdTrelloCardDto.getShortUrl());
        assertEquals(5, createdTrelloCardDto.getBadges().getVotes());
    }

    @Test
    void shouldCreateEmptyCreatedTrelloCardDto() {
        // Act
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto();

        // Assert
        assertEquals(null, createdTrelloCardDto.getId());
        assertEquals(null, createdTrelloCardDto.getName());
        assertEquals(null, createdTrelloCardDto.getShortUrl());
        assertEquals(null, createdTrelloCardDto.getBadges());
    }

    @Test
    void shouldCreateBadgesWithGivenParameters() {
        // Arrange
        CreatedTrelloCardDto.Badges badges = new CreatedTrelloCardDto.Badges();
        badges.setVotes(3);

        CreatedTrelloCardDto.AttachmentsByType attachments = new CreatedTrelloCardDto.AttachmentsByType();
        badges.setAttachmentsByType(attachments);

        // Act & Assert
        assertEquals(3, badges.getVotes());
        assertEquals(attachments, badges.getAttachmentsByType());
    }

    @Test
    void shouldCreateAttachmentsByTypeWithGivenParameters() {
        // Arrange
        CreatedTrelloCardDto.AttachmentsByType attachments = new CreatedTrelloCardDto.AttachmentsByType();
        attachments.setTrello(new CreatedTrelloCardDto.Trello());

        // Act
        attachments.getTrello().setBoard(2);
        attachments.getTrello().setCard(1);

        // Assert
        assertEquals(2, attachments.getTrello().getBoard());
        assertEquals(1, attachments.getTrello().getCard());
    }
}