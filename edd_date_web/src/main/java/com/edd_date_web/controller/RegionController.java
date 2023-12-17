package com.edd.date.controller;

import com.edd.date.domain.Post.RegionDTO;
import com.edd.date.entity.DateRegion;
import com.edd.date.service.RegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "데이트 장소 Sort API")
@RestController
@RequestMapping("/api")
public class RegionController {


    private final RegionService regionService;
    public RegionController(RegionService regionService){
        this.regionService = regionService;
    }
    @ApiOperation(value = "새로운 장소 넣기")
    @PostMapping("/region")
    public ResponseEntity<Map<String,String>> getRegionCreate(@RequestBody RegionDTO dto){
        return ResponseEntity.ok(regionService.RegionCreate(dto));
    }

    @ApiOperation(value = "게시글 작성 - 지역 리스트")
    @GetMapping("/region")
    public ResponseEntity<List<DateRegion>> getRegion(){
        return ResponseEntity.ok(regionService.Region());
    }



}
