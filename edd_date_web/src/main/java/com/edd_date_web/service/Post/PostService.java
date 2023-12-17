package com.edd.date.service.Post;

import com.edd.date.constants.WebConstants;
import com.edd.date.domain.Post.FeelingBoxDTO;
import com.edd.date.domain.Post.PostDTO;
import com.edd.date.domain.Post.PostThumbnailDTO;
import com.edd.date.domain.Post.mapper.FeelingBoxDTOMapper;
import com.edd.date.entity.Post.PostInfo;
import com.edd.date.mapper.Post.PostMapper;
import com.edd.date.querydsl.FeelingBoxQueryDSL;
import com.edd.date.querydsl.Post.PostInfoQueryDsl;
import com.edd.date.repository.Post.PostInfoRepository;
import com.edd.date.repository.Post.PostThumbnailImageRepository;
import com.edd.date.service.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper = PostMapper.INSTANCE;
    private final PostInfoQueryDsl postInfoQueryDsl;
    private final FeelingBoxQueryDSL feelingBoxQueryDSL;
    //========
    private final PostThumbnailImageRepository postThumbnailImageRepository;
    private final PostInfoRepository postInfoRepository;
    private final S3Uploader s3Uploader;





    public String PostCreate(List<MultipartFile> images) throws IOException {
        for (MultipartFile image : images) {
            String a = s3Uploader.uploadImageToS3(image,"png");
            System.out.println(a);
        }



        return WebConstants.OK;
    }

    public PostDTO PostDetail(long postSeq){

        PostInfo postInfo = postInfoQueryDsl.findById(postSeq);

        return postMapper.toPostDTO(
                postInfo,
                postInfo.getPostDetailImages(),
                postInfo.getPostThumbnailImages()
        );
    }

    public List<PostDTO> Post(Pageable pageable){

        return postInfoQueryDsl.findByAll(pageable)
                .stream()
                .map(postInfo -> postMapper.toPostDTO(
                        postInfo,
                        new ArrayList<>(),
                        postInfo.getPostThumbnailImages()
                        )
                )
                .collect(Collectors.toList());
    }

    public List<PostThumbnailDTO> PostThumbnail(String sort){

        return postInfoRepository.findAll(Sort.by(Sort.Direction.DESC,sort))
                .stream()
                .map(postInfo -> postMapper.toPostThumbnailDTO(
                        postInfo.getPostThumbnailImages().get(0),
                        postInfo.getPostSeq()
                )).collect(Collectors.toList());
    }

    public List<String> FeelingBox(){
        return feelingBoxQueryDSL.findDistinctFeeling();
    }
    public FeelingBoxDTO FeelingPost(String feelsOne, String feelsTwo, String sort){

        List<PostInfo> postInfos = postInfoQueryDsl.findByFeels(feelsOne,feelsTwo, sort);

        return FeelingBoxDTOMapper.DTOMapper(
                postInfos.size(),
                postInfos.stream()
                        .map(postInfo -> postMapper.toPostThumbnailDTO(
                                postInfo.getPostThumbnailImages().get(0),
                                postInfo.getPostSeq()
                        )).collect(Collectors.toList()));
    }

}
