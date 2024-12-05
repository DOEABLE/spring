package com.hana4.demo1.controller;

import com.hana4.demo1.dto.PostDTO;
import com.hana4.demo1.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping()
    public List<PostDTO> getPosts() {
//        logger.trace("tttttttttttttttttrace");
        logger.debug("dedededededededee-postcontroller!!!");
        logger.info("dddddddddddddddddd-postcontroller!!!");
        logger.warn("warning warning warning no! no!");
        logger.error("eeeeeeeeeeeeeeeee-postcontroller!!!");

        return service.getPosts();
    }

    @PostMapping()
    public PostDTO addPost(@RequestBody PostDTO post) {
        return service.addPost(post);
    }

    @GetMapping("/{id}")
    public PostDTO getPost(@PathVariable("id") String id) {
        System.out.println("id = " + id);
        return service.getPost(id);
    }

    @PatchMapping("/{id}")
    public PostDTO updatePost(@PathVariable("id") String id, @RequestBody PostDTO post) {
        post.setId(id);
        return service.modifyPost(post);
    }

    @DeleteMapping("/{id}")
    public PostDTO removePost(@PathVariable("id") String id) {
        System.out.println("delete.id = " + id);
        return service.removePost(id);
    }
}
