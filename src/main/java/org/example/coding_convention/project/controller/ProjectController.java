package org.example.coding_convention.project.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.coding_convention.common.BaseResponse;
import org.example.coding_convention.project.model.ProjectDto;
import org.example.coding_convention.project.service.ProjectService;
import org.example.coding_convention.user.model.User;
import org.example.coding_convention.user.model.UserDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "프로젝트")
@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @Operation(
            summary = "프로젝트 생성",
            description = "프로젝트의 이름, 설명 등을 담은 프로젝트가 생성됩니다."
    )
    @PostMapping("/create")
    public BaseResponse projectCreate(@RequestBody ProjectDto.ProjectReq dto, @AuthenticationPrincipal UserDto.AuthUser authUser) {
        projectService.save(dto, authUser);
        return BaseResponse.success("성공");
    }

    @Operation(
            summary = "프로젝트 목록 조회",
            description = "프로젝트의 내가 참여중인 프로젝트의 목록을 조회합니다."
    )
    @GetMapping("/list")
    public BaseResponse projectList(@AuthenticationPrincipal UserDto.AuthUser authUser) {
        List<ProjectDto.ProjectList> result = projectService.list(authUser);
        return BaseResponse.success(result);
    }

    @Operation(
            summary = "프로젝트 상세 조회",
            description = "내가 고른 프로젝트의 파일, 맴버, 채팅을 조회합니다."
    )
    @GetMapping("/read")
    public BaseResponse projectRead(@RequestParam Integer idx) {
        ProjectDto.ProejctRead result = projectService.read(idx);
        return BaseResponse.success(result);
    }

}
