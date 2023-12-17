package com.edd.date.entity.Post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostInfo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postSeq;

    @Enumerated(EnumType.STRING)
    private Status poplar;
    @Enumerated(EnumType.STRING)
    private Status suggestion;
    @Enumerated(EnumType.STRING)
    private Status advertisement;
    public enum Status{
        Y,N
    }

    private String title;
    private String content;

    private String feelsOne;
    private String feelsTwo;

    private String location; // 위치

    private String fullAddress;
    private String simpleAddress;//근데 이거 하나로 합치고 나눌 수 있지 않을까?

    private long cost;
    private long userSeq;
    private long likeIt;
    private String nickname;
    @CreationTimestamp
    private LocalDateTime signDate;


    @OneToMany(mappedBy = "postInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PostDetailImage> postDetailImages = new ArrayList<>();

    @OneToMany(mappedBy = "postInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PostThumbnailImage> postThumbnailImages = new ArrayList<>();

    public void add(PostDetailImage postDetailImage,
                    PostThumbnailImage postThumbnailImage){
        postDetailImage.setPostInfo(this);
        getPostDetailImages().add(postDetailImage);

        postThumbnailImage.setPostInfo(this);
        getPostThumbnailImages().add(postThumbnailImage);

    }

}
