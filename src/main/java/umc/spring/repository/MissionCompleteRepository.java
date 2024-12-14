package umc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Member;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MissionComplete;

public interface MissionCompleteRepository extends JpaRepository<MissionComplete, Long> {
    Page<MissionComplete> findAllByMemberAndStatus(Member member, MissionStatus status, Pageable pageable);
}
