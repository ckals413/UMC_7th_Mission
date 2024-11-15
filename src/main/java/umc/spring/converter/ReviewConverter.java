package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.dto.ReviewDto;

public class ReviewConverter {//ReviewDto도 받는다
    public static Review toReview(Member requestMember, ReviewDto dto, Store store){
        return Review.builder()
                .body(dto.getContent()) //리뷰 내용
                .score(dto.getScore()) //별점
                .member(requestMember)// 작성자
                .store(store)// 가게
                .build();
    }
}