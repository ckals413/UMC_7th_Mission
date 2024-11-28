package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.dto.MissionReqDto;

public class MissionConverter {
    public static Mission toMission(MissionReqDto request, Store store){
        return Mission.builder()
                .content(request.getContent())
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .store(store).build();
    }
}