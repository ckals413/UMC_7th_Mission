package umc.spring.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Terms;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.MissionStatus;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //enum
    @Enumerated(EnumType.STRING)
    private MissionStatus status;

    //N에 해당하는 엔티티가 1에 해당하는 앤티티와..
    //@ManytoOne 어노테이션
    @ManyToOne(fetch = FetchType.LAZY) //지연로딩
    @JoinColumn(name = "member_id", nullable = false) // 외래키의 이름 설정
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission; // 회원이참여한 미션

}