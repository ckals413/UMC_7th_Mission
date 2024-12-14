package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MissionComplete;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.dto.MissionResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class MissionCompleteConverter {
    public static MissionResponseDTO.CreateMemberMissionResultDTO toCreateMemberMissionResultDTO(MissionComplete missionComplete) {
        return MissionResponseDTO.CreateMemberMissionResultDTO.builder()
                .id(missionComplete.getId())
                .status(missionComplete.getStatus().toString())
                .missionContent(missionComplete.getMission().getContent())
                .missionReward(missionComplete.getMission().getReward())
                .build();
    }

    public static MissionComplete toMissionComplete(Member member, Mission mission){
        return MissionComplete.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGEING)
                .build();
    }

    public static MissionResponseDTO.MissionInProgressDTO toMissionInProgressDTO(MissionComplete missionComplete) {
        return MissionResponseDTO.MissionInProgressDTO.builder()
                .storeName(missionComplete.getMission().getStore().getName())
                .missionContent(missionComplete.getMission().getContent())
                .reward(missionComplete.getMission().getReward())
                .deadline(missionComplete.getMission().getDeadline().atStartOfDay())
                .status(missionComplete.getStatus())
                .build();
    }

    public static MissionResponseDTO.MissionInProgressListDTO toMissionInProgressListDTO(Page<MissionComplete> missionList) {
        List<MissionResponseDTO.MissionInProgressDTO> missionInProgressDTOList = missionList.stream()
                .map(MissionCompleteConverter::toMissionInProgressDTO)
                .collect(Collectors.toList());

        return MissionResponseDTO.MissionInProgressListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionInProgressDTOList.size())
                .missionList(missionInProgressDTOList)
                .build();
    }

}
