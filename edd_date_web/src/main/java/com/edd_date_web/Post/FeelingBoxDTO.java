package com.edd.date.domain.Post;

import lombok.Data;

import java.util.List;

@Data
public class FeelingBoxDTO {

    private long emotions;
    private List<PostThumbnailDTO> dtos;
}
