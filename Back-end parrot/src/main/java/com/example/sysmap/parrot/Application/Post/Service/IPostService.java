package com.example.sysmap.parrot.Application.Post.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.sysmap.parrot.Application.Comment.CommentRequest;
import com.example.sysmap.parrot.Application.Post.Dto.PostRequestReponse.PostReponse;
import com.example.sysmap.parrot.Application.Post.Dto.PostRequestReponse.PostRequest;
import com.example.sysmap.parrot.Damon.Entities.Comment;
import com.example.sysmap.parrot.Damon.Entities.Post;

public interface IPostService {
    public String createPost(String content,MultipartFile photo);
    public String deletePostbyId(String id);
    public String udpatePost(CommentRequest request,String postId);
    public List<PostReponse> findAllPost();
    public List<PostReponse> findPostById(String id);
    public Post getPostById (String id);
    public String createCommentPost(String postId, CommentRequest request);
    

}
