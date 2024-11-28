package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.mapping.MemberMission;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer reward; // 미션의 보상 포인트

    @Column(nullable = false)
    private LocalDate deadline; // 미션의 마감 시간


    @Column( nullable = false)
    private String content; //미션설명

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store; //미션 주체 가게

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberMission> memberMissions = new ArrayList<>(); // 미션에 참여한 회원 리스트

    @Override
    public String toString() {
        return "Mission{" +
                "id=" + id +
                ", reward=" + reward +
                ", deadline=" + deadline +
                ", content='" + content + '\'' +
                ", store=" + (store != null ? store.getName() : "N/A") + // store의 이름만 출력
                '}';
    }

}