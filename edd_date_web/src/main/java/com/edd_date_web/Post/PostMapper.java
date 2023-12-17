package com.edd.date.mapper.Post;

import com.edd.date.domain.Post.PostDTO;
import com.edd.date.domain.Post.PostThumbnailDTO;
import com.edd.date.entity.Post.PostDetailImage;
import com.edd.date.entity.Post.PostInfo;
import com.edd.date.entity.Post.PostThumbnailImage;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "postDetailImages", source = "postDetailImages")
    PostDTO toPostDTO(PostInfo postInfo,
                      List<PostDetailImage> postDetailImages,
                      List<PostThumbnailImage> postThumbnailImages);

    @Mapping(target = "postSeq", source = "postSeq")
    PostThumbnailDTO toPostThumbnailDTO(PostThumbnailImage postThumbnailImage,
                                        long postSeq);





}
