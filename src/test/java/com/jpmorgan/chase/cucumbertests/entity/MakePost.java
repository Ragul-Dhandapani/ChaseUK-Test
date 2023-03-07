package com.jpmorgan.chase.cucumbertests.entity;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import lombok.*;

@Data
@Builder
public class MakePost {

    @Expose
    private int userId;
    private int id;
    @Expose
    private String title;
    @Expose
    private String body;

    public String toStringJsonWithId() {
        return new GsonBuilder().create().toJson(this);
    }

    public String toStringJsonWithoutId() {
        MakePost makePostWithoutId = MakePost.builder()
                .userId(this.userId)
                .title(this.title)
                .body(this.body)
                .build();
      return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation() // exclude fields without @Expose annotation
                .create().toJson(makePostWithoutId);
    }
}
