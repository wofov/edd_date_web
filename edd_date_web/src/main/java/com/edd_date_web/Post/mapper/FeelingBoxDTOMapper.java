package com.edd.date.domain.Post.mapper;

import com.edd.date.domain.Post.FeelingBoxDTO;
import com.edd.date.domain.Post.PostThumbnailDTO;

import java.util.List;

public class FeelingBoxDTOMapper {

    public static FeelingBoxDTO DTOMapper(long number, List<PostThumbnailDTO> dtos){
        FeelingBoxDTO dto = new FeelingBoxDTO();
        dto.setEmotions(number);
        dto.setDtos(dtos);
        return dto;
    }
}
