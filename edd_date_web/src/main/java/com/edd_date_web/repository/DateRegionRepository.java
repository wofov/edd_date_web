package com.edd.date.repository;

import com.edd.date.entity.DateRegion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DateRegionRepository extends JpaRepository<DateRegion,Long> {

    DateRegion findByRegion(String region);
}
