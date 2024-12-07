package com.microservice.comment.Service;

import com.microservice.comment.Entity.Comment;
import com.microservice.comment.Repository.CommentRepository;
import com.microservice.comment.config.RestTemplateConfig;
import com.microservice.comment.payload.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RestTemplateConfig restTemplate;


    public Comment saveComment(Comment comment){

        //Check whether the post exist if yes the save the comment or else throws exception

        Post post = restTemplate.getRestTemplate().getForObject("http://POST-SERVICE/api/post/" + comment.getPostId(), Post.class);

        if(post!=null) {

            String randomCommentId = UUID.randomUUID().toString();
            comment.setCommentId(randomCommentId);
            comment.setPostId(post.getId());
            Comment savedComment = commentRepository.save(comment);
            return savedComment;
        }
        return null;
    }

    public List<Comment> getCommentByPostId(String postId){
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments;
    }
}



