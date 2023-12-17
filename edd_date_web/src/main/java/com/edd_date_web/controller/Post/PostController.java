package com.edd.date.controller.Post;

import com.edd.date.domain.Post.FeelingBoxDTO;
import com.edd.date.domain.Post.PostDTO;
import com.edd.date.domain.Post.PostThumbnailDTO;
import com.edd.date.entity.Post.PostInfo;
import com.edd.date.entity.Post.PostThumbnailImage;
import com.edd.date.service.Post.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "게시글 API")
@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;
    public PostController(PostService postService){
        this.postService = postService;
    }

    @ApiOperation(value = "Post Create(미완료)")
    @PutMapping("/posts")//@RequestBody PostDTO dto
    public ResponseEntity<String> getPostCreate(@RequestBody List<MultipartFile> images) throws IOException {
        return ResponseEntity.ok(postService.PostCreate(images));
    }

    @ApiOperation(value = "Post Detail")
    @GetMapping("/posts/{postSeq}")
    public ResponseEntity<PostDTO> getPostDetail(@PathVariable long postSeq){
        return ResponseEntity.ok(postService.PostDetail(postSeq));
    }

    @ApiOperation(value = "Post Paging")
    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> getPost(
            @PageableDefault(size = 5,direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok(postService.Post(pageable));
    }

    @ApiOperation(value = "Post Thumbnail Image List")
    @GetMapping("/posts/thumbnails/{sort}")
    public ResponseEntity<List<PostThumbnailDTO>> getThumbnail(
            @ApiParam(value = "likeIt -> 인기순 / signDate -> 최신순",required = true)
            @PathVariable String sort){
        return ResponseEntity.ok(postService.PostThumbnail(sort));
    }

    @ApiOperation(value = "FeelingBox Lookup")
    @GetMapping("/feeling")
    public ResponseEntity<List<String>> getFeelingBox(){
        return ResponseEntity.ok(postService.FeelingBox());
    }

    @ApiOperation(value = "Feeling Post Search")
    @GetMapping("/posts/feeling/{sort}")
    public ResponseEntity<FeelingBoxDTO> getFeelingPost(
            @ApiParam(value = "느낌 최대 2개")
            @RequestParam(required = false,defaultValue = "") String feelsOne,
            @RequestParam(required = false,defaultValue = "") String feelsTwo,
            @ApiParam(value = "likeIt -> 인기순 / signDate -> 최신순",required = true)
            @PathVariable String sort
    ){
        return ResponseEntity.ok(postService.FeelingPost(feelsOne,feelsTwo,sort));
    }



}
