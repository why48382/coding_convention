package org.example.coding_convention.project_member.controller;

import lombok.RequiredArgsConstructor;
import org.example.coding_convention.common.BaseResponse;
import org.example.coding_convention.project_member.model.ProjectMemberDto;
import org.example.coding_convention.project_member.service.ProjectMemberService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project/member")
@RequiredArgsConstructor
public class ProjectMemberController {
    private final ProjectMemberService projectService;

    @PostMapping("/create")
    public BaseResponse projectCreate(@RequestBody ProjectMemberDto.ProjectMemberReq dto) {
        projectService.save(dto);
        return BaseResponse.success("성공");
    }

}
