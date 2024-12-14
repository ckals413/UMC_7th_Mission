package umc.spring.service.MissionService;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    @Override
    public Optional<Mission> findMission(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Mission> findMissionsByMemberIdAndStatus(Long memberId, MissionStatus status) {
        return List.of();
    }

    @Override
    public Page<Mission> getMissionsByRegion(Long regionId, Pageable pageable) {
        return null;
    }
}
