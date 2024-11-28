package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.dto.ReviewRequestDto;

public class ReviewConverter {//ReviewDto도 받는다
    public static Review toReview(Member requestMember, ReviewRequestDto request, Store store){
        return Review.builder()
                .content(request.getContent())
                .score(request.getScore())
                .member(requestMember)// 작성자
                .store(store)// 가게
                .build();
    }
}