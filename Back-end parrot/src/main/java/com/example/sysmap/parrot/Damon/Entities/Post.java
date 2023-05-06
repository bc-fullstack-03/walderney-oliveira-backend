package com.example.sysmap.parrot.Damon.Entities;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Post {
    
    @Id
    private UUID id;
    private String comment;
    private Profile profile;
    private ArrayList<Like>likes;
    private ArrayList<Comment> comments;

}
