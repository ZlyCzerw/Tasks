package com.crud.tasks.trello.client;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloClientTest {

    @InjectMocks
    private TrelloClient trelloClient;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private TrelloConfig trelloConfig;

    @Before public void init() {
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        when(trelloConfig.getTrelloAppToken()).thenReturn("test");
        when(trelloConfig.getTrelloAppUsername()).thenReturn("wiktorm3");
    }
    @Test
    public void shouldFetchTrelloBoards() throws URISyntaxException {

        //Given
        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
        trelloBoards[0] = new TrelloBoardDto(new ArrayList<>(), "test_id", "test_board", false );

        URI uri = new URI("http://test.com/members/wiktorm3/boards?key=test&token=test&lists=all&fields=name,id");

        when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(trelloBoards);
        //When
        List<TrelloBoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();

        //Then
        Assert.assertEquals(1,trelloBoards.length);
        Assert.assertEquals(1, fetchedTrelloBoards.size());
        Assert.assertEquals("test_board", fetchedTrelloBoards.get(0).getId());
        Assert.assertEquals("test_id", fetchedTrelloBoards.get(0).getName());
        Assert.assertEquals(new ArrayList<>(), fetchedTrelloBoards.get(0).getLists());

    }

    @Test
    public void  shouldCreateCard() throws URISyntaxException {

      //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "Test task",
                "Test Description",
                "top",
                "test_id"
        );

        URI uri = new URI("http://test.com/cards?key=test&token=test&name=Test%20task&desc=Test%20Description&pos=top&idList=test_id");

        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto(
                "1",
                "Test task",
                "http://test.com",
                new BadgesDto(1,new AttachmentByTypesDto(new TrelloDto(0,0)))
        );

        when(restTemplate.postForObject(uri, null, CreatedTrelloCardDto.class )).thenReturn(createdTrelloCardDto);

        //When
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);

        //Then
        Assert.assertEquals("1",newCard.getId());
        Assert.assertEquals("Test task",newCard.getName());
        Assert.assertEquals("http://test.com", newCard.getShortUrl());


    }

    @Test
    public void  shouldReturnEmptyList() throws URISyntaxException {

       // Given
        TrelloBoardDto[] trelloBoards = null;
        URI uri = new URI("http://test.com/members/wiktorm3/boards?key=test&token=test&lists=all&fields=name,id");

        when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(trelloBoards);

        //When
        List<TrelloBoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();

        //Then
        Assert.assertEquals(new ArrayList<>(), fetchedTrelloBoards);

    }

}