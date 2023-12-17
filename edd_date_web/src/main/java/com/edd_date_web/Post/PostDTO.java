package com.edd.date.domain.Post;

import com.edd.date.entity.Post.PostDetailImage;
import com.edd.date.entity.Post.PostThumbnailImage;
import lombok.Data;

import java.util.List;

@Data
public class PostDTO {

    private long postSeq;

    private String poplar;
    private String suggestion;
    private String advertisement;

    private String title;
    private String content;
    private String feelsOne;
    private String feelsTwo;
    private String location; // 위치

    private String fullAddress;
    private String simpleAddress;

    private long cost;
    private long userSeq;
    private String nickname;
    private long likeIt;

    private List<PostDetailImage> postDetailImages;
    private List<PostThumbnailImage> postThumbnailImages;


}
