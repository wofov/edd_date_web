package com.edd.date.service.Admin;

import com.edd.date.config.security.JwtTokenProvider;
import com.edd.date.configException.CustomException;
import com.edd.date.configException.ErrorCode;
import com.edd.date.constants.WebConstants;
import com.edd.date.domain.Post.TokenDecoderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
@Component
public class AdminService {

    @Value("${myapp.code}")
    private String myAppCode;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenDecoderService tokenDecoderService;



    public String codeAuthenticate(String code,
                                   String authenticate){

        //@Component 있어야 함 . 빈으로 만들어야함.
        System.out.println(myAppCode);

        return code.equals(authenticate) ? "ok" : "fail";

    }

    public String JWT() throws CustomException{
        try{

            return jwtTokenProvider.createToken(1L,"id");

        }catch (Exception e){

            throw new CustomException(
                    WebConstants.EXCEPTION_500, ErrorCode.INTER_SERVER_ERROR
            );

        }
    }

    public String JWTDecoder(HttpServletRequest req) throws CustomException{

        try{

            return tokenDecoderService.decoderJWT(req, WebConstants.CLAIMS_ID);

        }catch (Exception e){

            throw new CustomException(
                    WebConstants.EXCEPTION_500, ErrorCode.TOKEN_DECODER_ERROR
            );

        }

    }

}
