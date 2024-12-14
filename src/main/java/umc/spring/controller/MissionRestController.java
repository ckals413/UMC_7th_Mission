package umc.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionCompleteConverter;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.mapping.MissionComplete;
import umc.spring.dto.MissionRequestDTO;
import umc.spring.dto.MissionResponseDTO;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.service.MissionService.MissionQueryService;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.validation.annotation.ValidPage;
import umc.spring.validation.validator.PageValidator;

@RequiredArgsConstructor
@RestController
@RequestMapping("/missions")
public class MissionRestController {
    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    @PostMapping("/{storeId}/mission")
    public ApiResponse<MissionResponseDTO.CreateResultDTO> createMission(
            @RequestParam("memberId") @ExistMember Long memberId,
            @RequestBody @Valid MissionRequestDTO.CreateDTO request,
            @PathVariable("storeId") @ExistStore Long storeId){
        Mission mission = missionCommandService.createMission(request, storeId, memberId);
        return ApiResponse.onSuccess(MissionConverter.toCreateResultDTO(mission));
    }

    @PostMapping("/{missionId}/addMission")
    public ApiResponse<Object> addChallengeMission(
            @PathVariable("missionId") Long missionId,
            @RequestParam("memberId") @ExistMember Long memberId){

        MissionComplete missionComplete = missionCommandService.addMissionToMember(missionId, memberId);
        return ApiResponse.onSuccess(MissionCompleteConverter.toCreateMemberMissionResultDTO(missionComplete));
    }

    @GetMapping("/{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게의 미션들의 목록을 조회하는 API, 페이징을 포함.  query String으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
    })
    public ApiResponse<MissionResponseDTO.MissionPreViewListDTO> getMissionList(
            @ExistStore @PathVariable(name = "storeId") Long storeId,
            @ValidPage @RequestParam(name = "page") Integer page) {
        Page<Mission> missionList = missionQueryService.getMissionList(storeId, PageValidator.convertPage(page));
        return ApiResponse.onSuccess(MissionConverter.missionPreViewListDTO(missionList));
    }

    @GetMapping("/progress")
    @Operation(summary = "내가 진행중인 미션 목록 조회 API", description = "로그인한 사용자의 진행중인 미션 목록을 조회하는 API이며, 페이징을 포함합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원 ID입니다.", required = true),
    })
    public ApiResponse<MissionResponseDTO.MissionInProgressListDTO> getInProgressMissions(
            @RequestParam("memberId") @ExistMember Long memberId,
            @ValidPage @RequestParam(name = "page") Integer page
    ) {
        Page<MissionComplete> missionList = missionQueryService.getInProgressMissions(memberId, PageValidator.convertPage(page));
        return ApiResponse.onSuccess(MissionCompleteConverter.toMissionInProgressListDTO(missionList));
    }

}
