package umc.spring.repository.MissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;


// 커스텀 레포지토리 인터페이스를 만들어 필요한 쿼리 메서드를 선언
public interface MissionRepositoryCustom {
    Page<Mission> findMissionsByMemberStatus(Long memberId, MissionStatus status, Pageable pageable);
}