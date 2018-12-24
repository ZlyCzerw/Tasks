package com.crud.tasks.domain;

public class TaskDto {

    private Long id;
    private String title;
    private String content;

    @java.beans.ConstructorProperties({"id", "title", "content"})
    public TaskDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public TaskDto() {
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }
}
