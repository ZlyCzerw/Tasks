package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TrelloMapperTestSuite {

    TrelloMapper trelloMapper = new TrelloMapper();
    String name = "CardOne";
    String desc = "test card one";
    String pos = "top";
    String cardId = "123";
    String listId = "1233";
    String boardId = "666";

    @Test
    public void mapCardtoCardDto(){
        //given
        TrelloCard trelloCard = new TrelloCard(name,desc,pos,cardId );
        //when
        TrelloCardDto mappedDto = trelloMapper.mapToTrelloCardDto(trelloCard);
        //then
        assertEquals(name, mappedDto.getName());
    }
    @Test
    public void mapCardDtoToCard(){
        //given
        TrelloCardDto trelloCardDto = new TrelloCardDto(name,desc,pos,cardId);
        //when
        TrelloCard mappedCard = trelloMapper.mapToTrelloCard(trelloCardDto);
        //then
        assertEquals(desc,mappedCard.getDescription());
    }
    @Test
    public void listToListDto(){
       //Given
        TrelloList trelloList = new TrelloList(listId,name,true);
        List<TrelloList> list = new ArrayList<>();
        list.add(trelloList);
       //When
        List<TrelloListDto> mappedListDto = trelloMapper.mapToListDto(list);
        System.out.println(mappedListDto.get(0).getName());
        //Then
        assertEquals(name,mappedListDto.get(0).getName());
    }
    @Test
    public void listDtoToList() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto(listId, name, true);
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
        TrelloListDto trelloListDto = new TrelloListDto(listId, name, true);
        List<TrelloListDto> list = new ArrayList<>();
        list.add(trelloListDto);
        TrelloBoardDto boardDto = new TrelloBoardDto(list,name,boardId,false);
        //When
        TrelloBoard board = trelloMapper.mapToBoard(boardDto);
        System.out.println(board.getId());
        //Then
        assertEquals(boardId,board.getId());
    }
    @Test
    public void mapBoardToBoardDtoLists(){
        //Given
        TrelloList trelloList = new TrelloList(listId,name,true);
        List<TrelloList> list = new ArrayList<>();
        list.add(trelloList);
        TrelloBoard board = new TrelloBoard(boardId,name,list,false);
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
        TrelloListDto trelloListDto = new TrelloListDto(listId, name, true);
        List<TrelloListDto> list = new ArrayList<>();
        list.add(trelloListDto);
        TrelloBoardDto boardDto = new TrelloBoardDto(list,name,boardId,false);
        List<TrelloBoardDto> boardsDtoList = new ArrayList<>();
        boardsDtoList.add(boardDto);
        //When
        List <TrelloBoard> boardsList = trelloMapper.mapToBoards(boardsDtoList);
        System.out.println(boardDto.getId());
        //Then
        assertEquals(boardId,boardsList.get(0).getId());
    }
}