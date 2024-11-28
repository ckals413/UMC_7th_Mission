package umc.spring.service.mission;

import umc.spring.domain.Mission;
import umc.spring.dto.MissionReqDto;

public interface MissionCommandService {
    Mission createMission(MissionReqDto request, Long storeId, Long memberId);
}