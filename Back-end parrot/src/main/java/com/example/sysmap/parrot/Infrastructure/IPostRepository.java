package com.example.sysmap.parrot.Infrastructure;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.sysmap.parrot.Damon.Entities.Post;

public interface IPostRepository extends  MongoRepository<Post, UUID>  {
    
}
