package umc.spring.service.ReviewService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;

public interface ReviewQueryService {
    //특정 가게의 리뷰 목록
    Page<Review> getReviewList(Long StoreId, Integer page);
    //특정 멤버 리뷰 목록
    Page<Review> getMemberReviewList(Long memberId, Integer page);

}