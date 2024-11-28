package umc.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.domain.response.MissionResponse;
import umc.spring.dto.MissionReqDto;
import umc.spring.service.mission.MissionCommandService;
import umc.spring.validation.annotation.ExistStore;

@RequiredArgsConstructor
@RestController
@RequestMapping("/missions")
public class MissionRestController {
    private final MissionCommandService missionCommandService;

    @Operation(
            summary = "미션 만들기",
            description = "주어진 정보를 바탕으로 가게에 미션을 생성합니다."
    )
    @Parameter(name="storeId",description = "가게 Id, path variable 입니다.",example = "1")
    @PostMapping("/{storeId}/mission")
    public ApiResponse<MissionResponse> createMission(
            @RequestBody @Valid MissionReqDto request,
            @PathVariable("storeId") @ExistStore Long storeId){
        Long memberId = 1L;
        return ApiResponse.onSuccess(new MissionResponse(missionCommandService.createMission(request,storeId,memberId)));
    }
}
