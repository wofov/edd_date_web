package com.edd.date.querydsl.Post;

import com.edd.date.constants.WebConstants;
import com.edd.date.domain.Post.PostDTO;
import com.edd.date.entity.Post.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class PostInfoQueryDsl {

    private final JPAQueryFactory queryFactory;
    private final QPostInfo postInfo = QPostInfo.postInfo;
    private final QPostDetailImage qPostDetailImage = QPostDetailImage.postDetailImage;
    private final QPostThumbnailImage qPostThumbnailImage = QPostThumbnailImage.postThumbnailImage;

    public List<PostInfo> findAll(){
        return queryFactory.selectFrom(postInfo).fetch();
    }

    public PostInfo findById(long postSeq){

        QPostInfo qPostInfo = QPostInfo.postInfo;
        QPostDetailImage qPostDetailImage = QPostDetailImage.postDetailImage;
        QPostThumbnailImage qPostThumbnailImage = QPostThumbnailImage.postThumbnailImage;

        PostInfo postInfo = queryFactory
                .selectFrom(qPostInfo)
                .where(qPostInfo.postSeq.eq(postSeq))
                .distinct()
                .fetchOne();

        if (postInfo != null) {
            List<PostDetailImage> postDetailImages = queryFactory
                    .selectFrom(qPostDetailImage)
                    .where(qPostDetailImage.postInfo.postSeq.eq(postSeq))
                    .distinct()
                    .fetch();

            List<PostThumbnailImage> postThumbnailImages = queryFactory
                    .selectFrom(qPostThumbnailImage)
                    .where(qPostThumbnailImage.postInfo.postSeq.eq(postSeq))
                    .distinct()
                    .fetch();

            postInfo.setPostDetailImages(postDetailImages);
            postInfo.setPostThumbnailImages(postThumbnailImages);
        }

        return postInfo;
    }

    public Page<PostInfo> findByAll(Pageable pageable){

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        booleanBuilder.and(postInfo.poplar.eq(PostInfo.Status.valueOf("Y")));

        long total = queryFactory
                .select(postInfo.count())
                .from(postInfo)
                .where(booleanBuilder)
                .stream().count();

        List<Long> ids = queryFactory
                .select(postInfo.postSeq)
                .from(postInfo)
                .where(booleanBuilder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<PostInfo> content = queryFactory
                .selectFrom(postInfo)
                .where(postInfo.postSeq.in(ids).and(booleanBuilder))
                .orderBy(postInfo.postSeq.desc())
                .fetch();

        for(PostInfo post : content) {

            List<PostThumbnailImage> postThumbnailImages = queryFactory
                    .selectFrom(qPostThumbnailImage)
                    .where(qPostThumbnailImage.postInfo.postSeq.eq(post.getPostSeq()))
                    .distinct()
                    .fetch();

            post.setPostThumbnailImages(postThumbnailImages);
        }

        return new PageImpl<>(content, pageable, total);

    }

    public List<PostInfo> findByFeels(String feelsOne,String feelsTwo,String sort){
        BooleanBuilder whereClaus = new BooleanBuilder();

        if (!feelsOne.equals(WebConstants.GAP) && feelsTwo.equals(WebConstants.GAP)) {
            whereClaus.or(
                    postInfo.feelsOne.eq(feelsOne).or(postInfo.feelsTwo.eq(feelsOne))
            );
        } else if (!feelsTwo.equals(WebConstants.GAP)) {
            whereClaus.or(
                    postInfo.feelsOne.eq(feelsOne).and(postInfo.feelsTwo.eq(feelsTwo)).or(
                    postInfo.feelsOne.eq(feelsTwo).and(postInfo.feelsTwo.eq(feelsOne)))
            );
        }

        OrderSpecifier<?> orderSpecifier = (sort.equals(WebConstants.SIGN_DATE))
                ? postInfo.signDate.desc()
                : postInfo.likeIt.desc();

        return queryFactory
                .select(postInfo)
                .from(postInfo)
                .where(whereClaus)
                .orderBy(orderSpecifier)
                .fetch();
    }





}
