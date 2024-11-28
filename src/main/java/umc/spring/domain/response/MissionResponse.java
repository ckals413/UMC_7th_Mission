package umc.spring.domain.response;

import lombok.*;
import umc.spring.domain.Mission;

import java.time.LocalDate;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
public class MissionResponse {
    private Long id;
    private String content;
    private Integer reword;
    private LocalDate deadline;
    public MissionResponse(Mission mission) {
        this.id = mission.getId();
        this.content = mission.getContent();
        this.reword = mission.getReward();
        this.deadline = mission.getDeadline();
    }
}
