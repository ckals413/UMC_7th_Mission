package umc.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.domain.response.ReviewResponse;
import umc.spring.dto.ReviewRequestDto;
import umc.spring.service.ReviewService.ReviewCommandService;
import umc.spring.validation.annotation.ExistStore;


@Validated
@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreRestController {
    private final ReviewCommandService reviewCommandService;
    @Operation(
            summary = "리뷰 작성하기",
            description = "특정 가게에 리뷰를 추가합니다."
    )
    @Parameter(name="storeId", description = "가게 Id, path variable 입니다.",example = "1")
    @PostMapping("/{storeId}/review")
    public ApiResponse<ReviewResponse> createReview(
            @PathVariable("storeId") @ExistStore Long storeId,
            @RequestBody @Valid ReviewRequestDto request) {
        Long memberId = 1L;
        return ApiResponse.onSuccess(new ReviewResponse(reviewCommandService.createReview(request, storeId, memberId)));
    }
}