package com.example.sysmap.parrot.Damon.Entities;

import java.util.ArrayList;
import java.util.UUID;

import lombok.Data;

@Data
public class Comment {
    private UUID id;
    private String context;
    private ArrayList<Like> likes;
    
}
