package org.example.coding_convention.project.controller;

import lombok.RequiredArgsConstructor;
import org.example.coding_convention.common.BaseResponse;
import org.example.coding_convention.project.model.ProjectDto;
import org.example.coding_convention.project.service.ProjectService;
import org.example.coding_convention.user.model.User;
import org.example.coding_convention.user.model.UserDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("/create")
    public BaseResponse projectCreate(@RequestBody ProjectDto.ProjectReq dto, @AuthenticationPrincipal UserDto.AuthUser authUser) {
        projectService.save(dto, authUser);
        return BaseResponse.success("성공");
    }

    @GetMapping("/list")
    public BaseResponse projectList(@AuthenticationPrincipal UserDto.AuthUser authUser) {
        List<ProjectDto.ProjectList> result = projectService.list(authUser);
        return  BaseResponse.success(result);
    }

    @GetMapping("/read")
    public BaseResponse projectRead(@RequestParam Integer idx) {
        ProjectDto.ProejctRead result = projectService.read(idx);
        return BaseResponse.success(result);
    }

}
