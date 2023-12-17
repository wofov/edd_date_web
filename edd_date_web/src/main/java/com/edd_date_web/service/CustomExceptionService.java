package com.edd.date.service;

import com.edd.date.configException.CustomException;
import com.edd.date.configException.ErrorCode;
import com.edd.date.constants.WebConstants;
import com.edd.date.entity.Post.PostInfo;
import com.edd.date.querydsl.Post.PostInfoQueryDsl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomExceptionService {


    private final PostInfoQueryDsl queryDsl;


    public List<PostInfo> postInfos(){
        return queryDsl.findAll();
    }

    public String CustomExceptionErrorEx() throws CustomException {

        try{

            long testLong = Long.parseLong("test error");

            return WebConstants.OK;

        }catch (Exception e){

            throw new CustomException(WebConstants.EXCEPTION_500, ErrorCode.TOKEN_DECODER_ERROR);

        }

    }

    public String CustomExceptionErrorEx2() throws CustomException {

        if(WebConstants.FAIL.equals(WebConstants.GAP)){
            throw new CustomException(WebConstants.EXCEPTION_500, ErrorCode.TOKEN_DECODER_ERROR);
        }

        return WebConstants.OK;

    }




}
