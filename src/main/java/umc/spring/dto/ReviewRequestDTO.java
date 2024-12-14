package umc.spring.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ReviewRequestDTO {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class InsertReviewDTO {
        @NotNull(message = "평점을 반드시 입력하세요.")
        @DecimalMin(value = "0.0", message = "별점은 최소 0.0 이상이어야 합니다.")
        @DecimalMax(value = "5.0", message = "별점은 최대 5.0 이하이어야 합니다.")
        @Schema(description = "평점", example = "3.9", type = "number")
        private Float score; // 리뷰 평점

        @NotBlank(message = "리뷰 내용를 반드시 입력하세요!")
        @Size(max = 300)
        @Schema(description = "리뷰 내용", example = "맛집입니다!", type="String")
        private String content; // 리뷰 내용
    }
}
