package com.edd.date.entity.Post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostReview {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reviewSeq;

    private long postInfoSeq;
    private String contents;
    private String nickname;
    private long likeIt;
    @CreationTimestamp
    private LocalDateTime signDate;

    @Enumerated(EnumType.STRING)
    private Relationship relationship;
    public enum Relationship{
        me, other
    }


}
