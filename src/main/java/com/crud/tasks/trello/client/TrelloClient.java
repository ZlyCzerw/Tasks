package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.config.TrelloConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;




@Component
public class TrelloClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloClient.class);

    @Autowired
    TrelloConfig trelloConfig;


    @Autowired
    private RestTemplate restTemplate;

    public List<TrelloBoardDto> getTrelloBoards() {

        try {
            TrelloBoardDto[] boardResponse = restTemplate.getForObject(
                    urlBuilder(),
                    TrelloBoardDto[].class);
            return Optional.ofNullable(boardResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(),e );
            return new ArrayList<>();
        }
    }


    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto){

        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloAppToken())
                .queryParam("name", trelloCardDto.getName() )
                .queryParam("desc", trelloCardDto.getDesc())
                .queryParam("pos", trelloCardDto.getPos())
                .queryParam("idList", trelloCardDto.getIdList()).build().encode().toUri();
        LOGGER.info("createNewCard " + url.toString());
        return restTemplate.postForObject(url, null, CreatedTrelloCard.class );
    }


    public URI urlBuilder(){
        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/members/" +trelloConfig.getTrelloAppUsername() +"/boards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloAppToken())
                .queryParam("lists", "all" )
                .queryParam("fields", "name,id").build().encode().toUri();

        return url;
    }
}
