package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BadgesDto {
    @JsonProperty("votes")
    private int votes;
    @JsonProperty("attachmentByType")
    private AttachmentByTypesDto attachmentsDto;


    public BadgesDto(int votes, AttachmentByTypesDto attachmentsDto) {
        this.votes = votes;
        this.attachmentsDto = attachmentsDto;
    }

    public BadgesDto() {
    }

    public int getVotes() {
        return this.votes;
    }

    public AttachmentByTypesDto getAttachmentsDto() {
        return this.attachmentsDto;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public void setAttachmentsDto(AttachmentByTypesDto attachmentsDto) {
        this.attachmentsDto = attachmentsDto;
    }
}
