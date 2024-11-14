package umc.spring.repository.MissionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Mission;

//<Mission,Long>
// JpaRepository가 관리할 엔티티 클레스 타입. = Mission, Long -> 엔티티의 기본키 타입
public interface MissionRepository extends JpaRepository<Mission, Long>, MissionRepositoryCustom {
}