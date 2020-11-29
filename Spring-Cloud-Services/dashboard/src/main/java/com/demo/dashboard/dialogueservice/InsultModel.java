package com.demo.dashboard.dialogueservice;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class InsultModel implements Serializable {

    private Integer number;
    private String language;
    @NotBlank
    private String insult;
    private String created;
    @JsonIgnore
    private String comment;
    @JsonIgnore
    private String createdby;
    @JsonIgnore
    private String shown;
    @JsonIgnore
    private Integer active;

    public InsultModel() {
    }

    public InsultModel(Integer number, String language, @NotBlank String insult, String created, String comment, String createdby, String shown, Integer active) {
        this.number = number;
        this.language = language;
        this.insult = insult;
        this.created = created;
        this.comment = comment;
        this.createdby = createdby;
        this.shown = shown;
        this.active = active;
    }

    public Integer getNumber() {
        return number;
    }

    public String getLanguage() {
        return language;
    }

    public String getInsult() {
        return insult;
    }

    public String getCreated() {
        return created;
    }

    public String getComment() {
        return comment;
    }

    public String getCreatedby() {
        return createdby;
    }

    public String getShown() {
        return shown;
    }

    public Integer getActive() {
        return active;
    }

    @Override
    public String toString() {
        return "InsultModel{" +
                "number=" + number +
                ", language='" + language + '\'' +
                ", insult='" + insult + '\'' +
                ", created='" + created + '\'' +
                ", comment='" + comment + '\'' +
                ", createdby='" + createdby + '\'' +
                ", shown='" + shown + '\'' +
                ", active=" + active +
                '}';
    }
}
