package umc.spring.repository.MissionRepository;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import umc.spring.domain.Mission;
import umc.spring.domain.QMission;
import umc.spring.domain.QStore;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.QMemberMission;

import java.util.List;


@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Mission> findMissionsByMemberStatus(Long memberId, MissionStatus status, Pageable pageable) {
        QMission mission = QMission.mission;
        QMemberMission memberMission = QMemberMission.memberMission;

        // BooleanBuilder로 동적 쿼리 생성
        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(memberMission.member.id.eq(memberId));

        // 상태가 지정되었을 경우 추가
        if (status != null) {
            predicate.and(memberMission.status.eq(status));
        }

        // 메인 쿼리 실행 (페이징 적용)
        List<Mission> missions = queryFactory
                .selectFrom(mission)
                .join(mission.memberMissions, memberMission)
                .where(predicate)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 카운트 쿼리 실행 (전체 데이터 개수 조회)
        long total = queryFactory
                .selectFrom(mission)
                .join(mission.memberMissions, memberMission)
                .where(predicate)
                .fetchCount();

        return new PageImpl<>(missions, pageable, total);
    }

    @Override
    public Page<Mission> findMissionsByRegion(Long regionId, Pageable pageable) {
        QMission mission = QMission.mission;
        QStore store = QStore.store;

        List<Mission> missions = queryFactory
                .selectFrom(mission)
                .join(mission.store, store).fetchJoin()// Fetch Join으로 지연 로딩 방지
                .where(store.region.id.eq(regionId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .selectFrom(mission)
                .join(mission.store, store)
                .where(store.region.id.eq(regionId))
                .fetchCount();

        return new PageImpl<>(missions, pageable, total);
    }

}