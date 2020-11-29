package com.demo.dashboard.dialogueservice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class Quote {

    @JsonIgnore
    private String dialogue;
    @JsonIgnore
    private Integer id;
    @JsonIgnore
    private String[] tags;
    @JsonIgnore
    private String url;
    @JsonIgnore
    @JsonProperty(value = "favorites_count")
    private Integer favCount;
    @JsonIgnore
    @JsonProperty(value = "upvotes_count")
    private Integer upCount;
    @JsonIgnore
    @JsonProperty(value = "downvotes_count")
    private Integer downCount;
    @JsonIgnore
    private String author;
    @JsonProperty(value = "author_permalink")
    @JsonIgnore
    private String authorLink;
    private String body;

    public Quote() {
    }

    public String getDialogue() {
        return dialogue;
    }

    public void setDialogue(String dialogue) {
        this.dialogue = dialogue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getFavCount() {
        return favCount;
    }

    public void setFavCount(Integer favCount) {
        this.favCount = favCount;
    }

    public Integer getUpCount() {
        return upCount;
    }

    public void setUpCount(Integer upCount) {
        this.upCount = upCount;
    }

    public Integer getDownCount() {
        return downCount;
    }

    public void setDownCount(Integer downCount) {
        this.downCount = downCount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorLink() {
        return authorLink;
    }

    public void setAuthorLink(String authorLink) {
        this.authorLink = authorLink;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "dialogue='" + dialogue + '\'' +
                ", id=" + id +
                ", tags=" + Arrays.toString(tags) +
                ", url='" + url + '\'' +
                ", favCount=" + favCount +
                ", upCount=" + upCount +
                ", downCount=" + downCount +
                ", author='" + author + '\'' +
                ", authorLink='" + authorLink + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
