package umc.spring.repository.MemberRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Member;

//Member 엔티티와 그 기본 키 타입 Long을 사용하여 기본 CRUD 메서드를 제공
public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
}
