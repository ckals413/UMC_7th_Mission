package umc.spring.Service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Member;
import umc.spring.repository.MemberRepository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberRepository memberRepository;

    @Override
    public Optional<Member> findMember(Long memberId) {
        //findById는 JpaRepository에서 제공하는 기본 메서드
        //memberId로 Member를 조회하고, 조회된 회원이 있을 경우 콘솔에 출력
        Optional<Member> findMember = memberRepository.findById(memberId);
        findMember.ifPresent(member -> System.out.println("Member : " + member));
        return findMember;
    }
}
