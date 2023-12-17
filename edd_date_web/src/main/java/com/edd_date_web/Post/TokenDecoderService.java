package com.edd.date.domain.Post;

import com.edd.date.config.security.JwtTokenProvider;
import com.edd.date.configException.CustomException;
import com.edd.date.configException.ErrorCode;
import com.edd.date.constants.WebConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TokenDecoderService {

    private final JwtTokenProvider jwtTokenProvider;

    public String decoderJWT(HttpServletRequest req, String claimsId) throws CustomException {

        String headerJWT = jwtTokenProvider.resolveToken(req);

        try{

            String payload = headerJWT.split(WebConstants.JWTDECODER)[1];
            Base64.Decoder decoder = Base64.getUrlDecoder();
            decoder.decode(payload);

            BasicJsonParser jsonParser = new BasicJsonParser();
            Map<String,Object> jsonArray = jsonParser.parseMap(new String(decoder.decode(payload)));

            return jsonArray.get(claimsId).toString();

        }catch (Exception e){

            throw new CustomException(WebConstants.EXCEPTION_500, ErrorCode.TOKEN_DECODER_ERROR);

        }

    }

}
