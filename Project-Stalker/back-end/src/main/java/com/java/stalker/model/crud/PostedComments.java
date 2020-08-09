package com.java.stalker.model.crud;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class PostedComments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long postId;

    @Column(name = "post_content")
    private String postComment;

    @Column(name = "post_creation_time")
    private Date postedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private EnrolledUser user;

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getPostComment() {
        return postComment;
    }

    public void setPostComment(String postComment) {
        this.postComment = postComment;
    }

    public Date getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Date postedAt) {
        this.postedAt = postedAt;
    }

    public EnrolledUser getUser() {
        return user;
    }

    public void setUser(EnrolledUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "PostedComments{" +
                "postId=" + postId +
                ", postComment='" + postComment + '\'' +
                ", postedAt=" + postedAt +
                ", user=" + user +
                '}';
    }
}
