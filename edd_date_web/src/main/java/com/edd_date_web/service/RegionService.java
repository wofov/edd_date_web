package com.edd.date.service;

import com.edd.date.constants.WebConstants;
import com.edd.date.domain.Post.RegionDTO;
import com.edd.date.entity.DateRegion;
import com.edd.date.repository.DateRegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RegionService {

    private final DateRegionRepository dateRegionRepository;

    public Map<String,String> RegionCreate(RegionDTO dto){

        if(dateRegionRepository.findByRegion(dto.getRegion()) != null){
            return Map.of(
                    WebConstants.STATUS,WebConstants.FAIL,
                    WebConstants.MESSAGE,WebConstants.OVERLAP
            );
        }

        DateRegion dateRegion = new DateRegion();
        dateRegion.setRegion(dto.getRegion());
        dateRegionRepository.save(dateRegion);

        return Map.of(WebConstants.STATUS,WebConstants.OK);
    }

    public List<DateRegion> Region(){
        return dateRegionRepository.findAll();
    }


}
