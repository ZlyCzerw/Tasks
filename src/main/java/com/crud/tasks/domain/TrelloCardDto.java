package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@lombok.Setter

public class TrelloCardDto {

    private String name;
    private String desc;
    private String pos;
    private String idList;

}
