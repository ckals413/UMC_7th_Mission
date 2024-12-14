package umc.spring.service.MissionService;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MissionComplete;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionCompleteRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.StoreRepository.StoreRepository;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MissionCompleteRepository missionCompleteRepository;
    private final MemberRepository memberRepository;

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

    @Override
    public Page<Mission> getMissionList(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId).get();
        return missionRepository.findAllByStore(store, PageRequest.of(page, 10));
    }

    @Override
    public Page<MissionComplete> getInProgressMissions(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId).get();
        return missionCompleteRepository.findAllByMemberAndStatus(
                member,
                MissionStatus.IN_PROGRESS,
                PageRequest.of(page, 10)
        );
    }

}
