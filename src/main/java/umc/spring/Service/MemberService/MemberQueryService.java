package umc.spring.Service.MemberService;

import umc.spring.domain.Member;

import java.util.Optional;

//로직에서 사용할 서비스 인터페이스
// findMember 메서드를 통해 특정 memberId에 해당하는 회원을 조회
public interface MemberQueryService {
    Optional<Member> findMember(Long memberId);
}