package com.microservice.post.Controller;





import com.microservice.post.Entity.Post;
import com.microservice.post.Service.PostService;
import com.microservice.post.payload.PostDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<Post> savePost(@RequestBody Post post){

        Post savedPost = postService.savePost(post);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);

    }

    @GetMapping("/{postId}")
    public Post getPostById(@PathVariable String postId){
        Post post = postService.getPostById(postId);
        return post;
    }


    @GetMapping("/{postId}/comments")
    @CircuitBreaker(name = "commentBreaker", fallbackMethod = "commentFallback")
    //  @Retry(name = "commentBreakerService", fallbackMethod = "commentFallback")
    public ResponseEntity<PostDto> getAllCommentsForParticularPost(@PathVariable String postId){

        PostDto postDto =  postService.getAllCommentsForParticularPost(postId);

        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    public ResponseEntity<PostDto> commentFallback(String postId, Exception ex) {

        System.out.println("Fallback is executed because service is down : "+ ex.getMessage());

        // ex.printStackTrace();

        PostDto dto = new PostDto();
        dto.setId("1234");
        dto.setTitle("Service Down");
        dto.setContent("Service Down");
        dto.setDescription("Service Down");
        //dto.setComments(Arrays.("Service Down"));
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}


