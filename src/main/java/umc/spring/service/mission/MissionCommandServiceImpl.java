package umc.spring.service.mission;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.dto.MissionReqDto;
import umc.spring.repository.MissionRepository.MissionRepository;
import umc.spring.repository.StoreRepository.StoreRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService{
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    @Override
    @Transactional
    public Mission createMission(MissionReqDto request, Long storeId, Long memberId) {
        Store store = storeRepository.findById(storeId).get();
        Mission newMission = MissionConverter.toMission(request, store);
        store.getMissionList().add(newMission);
        return missionRepository.save(newMission);
    }
}