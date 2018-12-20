package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrelloMapperTestSuite {

    @Autowired
    TrelloMapper trelloMapper;

    private TrelloCardDto createTrelloCardDto(){
        String name = "CardOne";
        String desc = "test card one";
        String pos = "top";
        String cardId = "123";
        TrelloCardDto trelloCardDto = new TrelloCardDto(name,desc,pos,cardId);
      return trelloCardDto;
    }
    private TrelloCard createTrelloCard() {
        String name = "CardOne";
        String desc = "test card one";
        String pos = "top";
        String cardId = "123";
        TrelloCard trelloCard = new TrelloCard(name, desc, pos, cardId);
        return trelloCard;
    }




    @Test
    public void mapCardtoCardDto(){
        //given
        TrelloCard trelloCard = createTrelloCard();
        //when
        TrelloCardDto mappedDto = trelloMapper.mapToTrelloCardDto(trelloCard);
        //then
        assertEquals("CardOne", mappedDto.getName());
    }
    @Test
    public void mapCardDtoToCard(){
        //given
        TrelloCardDto trelloCardDto = createTrelloCardDto();
        //when
        TrelloCard mappedCard = trelloMapper.mapToTrelloCard(trelloCardDto);
        //then
        assertEquals("test card one",mappedCard.getDescription());
    }
    @Test
    public void listToListDto(){
       //Given
        String listId = "1233";
        TrelloList trelloList = new TrelloList(listId,"lista",true);
        List<TrelloList> list = new ArrayList<>();
        list.add(trelloList);
       //When
        List<TrelloListDto> mappedListDto = trelloMapper.mapToListDto(list);
        System.out.println(mappedListDto.get(0).getName());
        //Then
        assertEquals("lista",mappedListDto.get(0).getName());
    }
    @Test
    public void listDtoToList() {
        //Given
        String listId = "1233";
        TrelloListDto trelloListDto = new TrelloListDto(listId, "lostaDto", true);
        List<TrelloListDto> list = new ArrayList<>();
        list.add(trelloListDto);
        //When
        List<TrelloList> mappedList = trelloMapper.mapToList(list);
        System.out.println(mappedList.get(0).getId());
        //Then
        assertEquals(listId, mappedList.get(0).getId());
    }


    @Test
    public void mapBoardDtoToBoard(){
        //Given
        String listId = "1233";
        String boardId = "666";
        TrelloListDto trelloListDto = new TrelloListDto(listId, "lostaDto", true);
        List<TrelloListDto> list = new ArrayList<>();
        list.add(trelloListDto);
        TrelloBoardDto boardDto = new TrelloBoardDto(list,"tablicaDto",boardId,false);
        //When
        TrelloBoard board = trelloMapper.mapToBoard(boardDto);
        System.out.println(board.getId());
        //Then
        assertEquals(boardId,board.getId());
    }
    @Test
    public void mapBoardToBoardDtoLists(){
        //Given
        String listId = "1233";
        String boardId = "666";
        TrelloList trelloList = new TrelloList(listId,"lista",true);
        List<TrelloList> list = new ArrayList<>();
        list.add(trelloList);
        TrelloBoard board = new TrelloBoard(boardId,"tablica",list,false);
        List<TrelloBoard> boardsList = new ArrayList<>();
        boardsList.add(board);
        //When
       List <TrelloBoardDto> boardDtoList = trelloMapper.mapToBoardsDto(boardsList);
        System.out.println(board.getId());
        //Then
        assertEquals(boardId,boardDtoList.get(0).getId());
    }
    @Test
    public void mapBoardDtoToBoardLists(){
        //Given
        String listId = "1233";
        String boardId = "666";
        TrelloListDto trelloListDto = new TrelloListDto(listId, "lostaDto", true);
        List<TrelloListDto> list = new ArrayList<>();
        list.add(trelloListDto);
        TrelloBoardDto boardDto = new TrelloBoardDto(list,"tablicaDto",boardId,false);
        List<TrelloBoardDto> boardsDtoList = new ArrayList<>();
        boardsDtoList.add(boardDto);
        //When
        List <TrelloBoard> boardsList = trelloMapper.mapToBoards(boardsDtoList);
        System.out.println(boardDto.getId());
        //Then
        assertEquals(boardId,boardsList.get(0).getId());
    }
}