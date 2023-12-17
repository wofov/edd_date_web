package com.edd.date.querydsl;

import com.edd.date.entity.QFeelingBox;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class FeelingBoxQueryDSL {

    private final JPAQueryFactory queryFactory;
    private final QFeelingBox feelingBox = QFeelingBox.feelingBox;

    public List<String> findDistinctFeeling(){
        return queryFactory
                .select(feelingBox.feeling)
                .distinct()
                .from(feelingBox)
                .fetch();
    }
}
