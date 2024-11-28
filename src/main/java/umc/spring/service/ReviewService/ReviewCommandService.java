package umc.spring.service.ReviewService;

import umc.spring.domain.Review;
import umc.spring.dto.ReviewRequestDto;

public interface ReviewCommandService {
    Review createReview(ReviewRequestDto request, Long storeId, Long memberId);
}