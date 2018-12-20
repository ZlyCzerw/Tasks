package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTest {

    @InjectMocks
    private TrelloFacade trelloFacade;

    @Mock
    private TrelloService trelloService;
    @Mock
    private TrelloValidator trelloValidator;
    @Mock
    private TrelloMapper trelloMapper;

@Test
    public void shouldFetchEmptyList(){
    //Given
    List<TrelloListDto> trelloListDtos = new ArrayList<>();
    trelloListDtos.add(new TrelloListDto("340", "Ogień i rozpuszczone w cieczach gazy szlachetne", false));

    List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
    trelloBoardDtos.add(new TrelloBoardDto(trelloListDtos, "tablica", "1111", false));

    List<TrelloList> mappedTrelloLists = new ArrayList<>();
    mappedTrelloLists.add(new TrelloList("65498","Spadnie klątwa na wszystkie rośliny okrytonasienne",false));

    List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
    mappedTrelloBoards.add(new TrelloBoard("7987","Aclibat",mappedTrelloLists,false));


    when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardDtos);
    when(trelloMapper.mapToBoards(trelloBoardDtos)).thenReturn(mappedTrelloBoards);
    when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(new ArrayList<>());
    when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(new ArrayList<>());
    //When
    List<TrelloBoardDto> trelloBoardDto =trelloFacade.fetchTrelloBoards();
    //Then
    assertNotNull(trelloBoardDto);
    assertEquals(0,trelloBoardDto.size());
}
@Test
    public void shouldFetchTrelloBoards(){
    //Given
    List<TrelloListDto> trelloListDtos = new ArrayList<>();
    trelloListDtos.add(new TrelloListDto("340", "Ogień i rozpuszczone w cieczach gazy szlachetne", false));

    List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
    trelloBoardDtos.add(new TrelloBoardDto(trelloListDtos, "tablica", "1111", false));

    List<TrelloList> mappedTrelloLists = new ArrayList<>();
    mappedTrelloLists.add(new TrelloList("65498","Spadnie klątwa na wszystkie rośliny okrytonasienne",false));

    List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
    mappedTrelloBoards.add(new TrelloBoard("7987","Aclibat",mappedTrelloLists,false));

    when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardDtos);
    when(trelloMapper.mapToBoards(trelloBoardDtos)).thenReturn(mappedTrelloBoards);
    when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(trelloBoardDtos);
    when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);
    //when
    List<TrelloBoardDto> trelloBoardDto =trelloFacade.fetchTrelloBoards();
    //then
    assertNotNull(trelloBoardDto);
    assertEquals(1,trelloBoardDto.size());

    trelloBoardDto.forEach(boardDto -> {
        assertEquals("1111",boardDto.getId());
        assertEquals("tablica",boardDto.getName());

        boardDto.getLists().forEach(trelloListDto -> {
            assertEquals("340",trelloListDto.getId());
            assertEquals(false, trelloListDto.isClosed());
            assertEquals("Ogień i rozpuszczone w cieczach gazy szlachetne", trelloListDto.getName());
        });
    });
}

}