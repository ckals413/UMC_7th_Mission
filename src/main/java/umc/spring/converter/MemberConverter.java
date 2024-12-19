package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.enums.Gender;
import umc.spring.dto.MemberRequestDTO;
import umc.spring.dto.MemberResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequestDTO.JoinDto request) {
        Gender gender = Gender.NONE;  // 기본값 설정

        if (request.getGender() != null) {  // null 체크 추가 , setter를 추가하기전 오류가나서 추가해뒀음..
            switch (request.getGender()) {
                case 1: gender = Gender.MALE; break;
                case 2: gender = Gender.FEMALE; break;
                case 3: gender = Gender.NONE; break;
                default: gender = Gender.NONE;
            }
        }

        return Member.builder()
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .email(request.getEmail())
               .password(request.getPassword())
                .role(request.getRole())
                .gender(gender)
                .name(request.getName())
                .memberPrefer(new ArrayList<>())
                .build();
    }
}
