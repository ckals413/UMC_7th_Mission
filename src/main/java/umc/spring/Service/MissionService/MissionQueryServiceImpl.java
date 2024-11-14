package umc.spring.Service.MissionService;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.repository.MissionRepository.MissionRepository;

import java.util.List;
import java.util.Optional;

// MissionQueryService의 구현체로, 미션 조회 관련 로직을 수행하는 서비스 클래스

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {
    private final MissionRepository missionRepository;

    /**
     * 특정 ID를 통해 미션을 조회
     * @param id 조회할 미션의 ID
     * @return 해당 ID의 미션을 Optional로 반환 (존재하지 않으면 Optional.empty())
     */
    @Override
    public Optional<Mission> findMission(Long id) {
        return missionRepository.findById(id);
    }

    /**
     * 특정 회원의 미션 상태를 기준으로 미션을 조회하고, 최신순으로 정렬하여 페이징 처리
     * @param memberId 회원의 ID
     * @param status 미션의 상태 (예: MissionStatus.IN_PROGRESS 또는 MissionStatus.COMPLETED)
     * @return 해당 회원의 상태별 미션 목록 (최신순으로 정렬, 한 페이지에 최대 10개의 미션)
     */
    @Override
    public List<Mission> findMissionsByMemberIdAndStatus(Long memberId, MissionStatus status) {
        // 0번째 페이지부터 10개씩, createdAt 필드 기준 내림차순 정렬로 페이징 설정
        Pageable pageable = PageRequest.of(0, 10, Sort.by("createdAt").descending());
        // 커스텀 메서드 findMissionsByMemberStatus를 통해 미션을 조회 (페이징 및 상태 필터링)
        Page<Mission> filterdMission = missionRepository.findMissionsByMemberStatus(memberId, status, pageable);
        // 각 미션 정보를 콘솔에 출력 (로그 목적)
        filterdMission.forEach(mission -> System.out.println("Mission : " + mission));
        // 조회된 미션의 콘텐츠(리스트)만 반환
        return filterdMission.getContent();
    }
}