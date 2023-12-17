package com.edd.date.entity.Post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"postInfo"})
public class PostThumbnailImage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long thumbnailImageSeq;

    @ManyToOne
    @JoinColumn(name = "postSeq")
    @JsonIgnore
    private PostInfo postInfo;

    private String thumbnailImage;


}
