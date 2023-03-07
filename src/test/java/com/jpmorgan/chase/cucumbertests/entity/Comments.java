package com.jpmorgan.chase.cucumbertests.entity;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import lombok.*;

@Data
@Builder
public class Comments {

    @Expose
    private int postId;

    private int id;

    @Expose
    private String name;
    @Expose
    private String email;
    @Expose
    private String body;

    public String toStringJsonWithId() {
        return new GsonBuilder().create().toJson(this);
    }

    public String toStringJsonWithoutId() {
        Comments makePostWithoutId = Comments.builder()
                .postId(this.postId)
                .name(this.name)
                .email(this.email)
                .body(this.body)
                .build();
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation() // exclude fields without @Expose annotation
                .create().toJson(makePostWithoutId);
    }
}
