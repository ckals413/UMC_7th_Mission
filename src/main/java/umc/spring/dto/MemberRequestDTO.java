package umc.spring.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import umc.spring.domain.enums.Role;
import umc.spring.validation.annotation.ExistCategories;

import java.util.List;

public class MemberRequestDTO {

    @Getter
     @Setter
    public static class JoinDto{
        @NotBlank(message = "이름을 반드시 입력하세요.")
        @Size(max = 15)
        @Schema(description = "이름", example = "임차민", type="string")
        String name;
        @NotNull(message = "성별을 반드시 입력하세요")
        @Schema(description = "성별", example = "1", type = "int")
        Integer gender;
        @NotNull(message = "반드시 입력하세요.")
        Integer birthYear;
        @NotNull(message = "반드시 입력하세요.")
        Integer birthMonth;
        @NotNull(message = "반드시 입력하세요.")
        Integer birthDay;
        @Size(min = 3, max = 40)
        @NotBlank(message = "주소는 필수,  최대 40자 까지 입력 가능")
        @Schema(description = "주소", example = "해솔로", type="string")
        String address;
        @Size(min = 5, max = 30)
        String specAddress;
        @ExistCategories
        List<Long> preferCategory;
        @NotBlank
        @Email
        String email;    // 이메일 필드 추가
        @NotBlank
        String password;    // 비밀번호 필드 추가
        @NotNull
        Role role;    // 역할 필드 추가
    }
}