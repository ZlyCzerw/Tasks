package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component
public class TrelloMapper {

    public TrelloBoard mapToBoard(final TrelloBoardDto trelloBoardDto){
        return new TrelloBoard(trelloBoardDto.getId(), trelloBoardDto.getName(), mapToList(trelloBoardDto.getLists()),trelloBoardDto.isClosed());
    }

    public List<TrelloList> mapToList(List <TrelloListDto> trelloListDto){
        return trelloListDto.stream()
                .map(trelloList -> new TrelloList(trelloList.getId(), trelloList.getName(), trelloList.isClosed()))
                .collect(toList());
    }
    public List<TrelloListDto> mapToListDto(List <TrelloList> trelloList) {
        return trelloList.stream()
                .map(List -> new TrelloListDto(List.getId(), List.getName(), List.isClosed()))
                .collect(toList());
    }


    public List<TrelloBoard> mapToBoards(final List<TrelloBoardDto> trelloBoardDto){
        return trelloBoardDto.stream()
                .map(trelloBoard -> new TrelloBoard(trelloBoard.getId(), trelloBoard.getName(), mapToList(trelloBoard.getLists()),trelloBoard.isClosed()))
                .collect(toList());
    }
    public List<TrelloBoardDto> mapToBoardsDto(final List<TrelloBoard> trelloBoards){
        return trelloBoards.stream()
                .map(trelloBoard -> new TrelloBoardDto(mapToListDto(trelloBoard.getLists()),trelloBoard.getName(),trelloBoard.getId(), trelloBoard.isClosed()))
                .collect(toList());

    }
    public TrelloCard mapToTrelloCard(final TrelloCardDto trelloCardDto){
        return  new TrelloCard(trelloCardDto.getName(), trelloCardDto.getDesc(), trelloCardDto.getPos(), trelloCardDto.getIdList());
    }
    public TrelloCardDto mapToTrelloCardDto(final TrelloCard trelloCard){
        return  new TrelloCardDto(trelloCard.getName(), trelloCard.getDescription(), trelloCard.getPos(), trelloCard.getListId());
    }


}
