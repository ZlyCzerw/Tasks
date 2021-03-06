package com.crud.tasks.controller;


import com.crud.tasks.domain.*;
import com.crud.tasks.service.DBService;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TrelloController.class)
public class TrelloControllerTestSuite {


  @Autowired
    MockMvc mockMvc;

    @MockBean
    TrelloFacade trelloFacade;

;

    @Test
    public void shouldFetchEmptyTrelloBoards() throws Exception{
        //Given
        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoardsDto);
        //When & Then
        mockMvc.perform(get("/v1/trello/getTrelloBoards").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$",hasSize(0)));
    }

    @Test
    public void shouldFetchTrelloBoards() throws Exception{
        //Given
      List<TrelloListDto>trelloListsDto = new ArrayList<>();
      trelloListsDto.add(new TrelloListDto("1","TestList",false));

      List<TrelloBoardDto>trelloBoardsDto = new ArrayList<>();
      trelloBoardsDto.add(new TrelloBoardDto(trelloListsDto,"testBoard","1",false));

      when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoardsDto);
      //When & Then
      mockMvc.perform(get("/v1/trello/getTrelloBoards").contentType(MediaType.APPLICATION_JSON))
              .andExpect(status().isOk())
              .andExpect(jsonPath("$",hasSize(1)))
              .andExpect(jsonPath("$[0].id",is("1")))
              .andExpect(jsonPath("$[0].name",is("testBoard")))
              .andExpect(jsonPath("$[0].lists[0].id",is("1")))
              .andExpect(jsonPath("$[0].lists[0].name",is("TestList")))
              .andExpect(jsonPath("$[0].lists[0].closed",is(false)));
    }
    @Test
    public void shouldCreateTrelloCard() throws Exception {
      //Given
      BadgesDto badgesDto = new BadgesDto();
      TrelloCard trelloCard= new TrelloCard("Card","test","top","1");
      CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "aaa","http://test.com",badgesDto);
      when(trelloFacade.createCard(ArgumentMatchers.any(TrelloCardDto.class))).thenReturn(createdTrelloCardDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(createdTrelloCardDto);

        //When & Then
        mockMvc.perform(post("/v1/trello/createTrelloCard")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is("1")))
                .andExpect(jsonPath("$.name", is("aaa")))
                .andExpect(jsonPath("$.shortUrl", is("http://test.com")));
    }


}