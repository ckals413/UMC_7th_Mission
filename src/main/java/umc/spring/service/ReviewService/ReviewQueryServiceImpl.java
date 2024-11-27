package umc.spring.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.dto.ReviewDto;
import umc.spring.repository.ReviewRepository.ReviewRepository;
import umc.spring.repository.StoreRepository.StoreRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService{
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    @Transactional
    public Review createReview(Member requestMember, ReviewDto reviewDto, Long storeId){
        Store store = storeRepository.findById(storeId).get();
        Review review = ReviewConverter.toReview(requestMember, reviewDto, store);
        return reviewRepository.save(review);
    }
}