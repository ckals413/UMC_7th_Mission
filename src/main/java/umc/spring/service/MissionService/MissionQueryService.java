package umc.spring.service.MissionService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MissionComplete;

import java.util.List;
import java.util.Optional;

//MissionQueryService 인터페이스는 미션 조회와 관련된 로직을 정의한다.
public interface MissionQueryService {

    /**
     * ID를 사용하여 특정 미션을 조회
     * id = 조회할 미션의 ID
     * @return 미션을 Optional로 반환 (존재하지 않으면 Optional.empty())
     */
    Optional<Mission> findMission(Long id);

    /**
     * 특정 회원의 미션 상태에 따라 미션을 조회합니다.
     *  memberId = 회원의 ID
     *  status = 미션의 상태 (예: IN_PROGRESS, COMPLETED)
     * @return 조회된 미션 목록
     */

    List<Mission> findMissionsByMemberIdAndStatus(Long memberId, MissionStatus status);
    Page<Mission> getMissionsByRegion(Long regionId, Pageable pageable);
    Page<Mission> getMissionList(Long storeId, Integer page);
    // 특정 멤버가 진행중인 미션
    Page<MissionComplete> getInProgressMissions(Long memberId, Integer page);

}