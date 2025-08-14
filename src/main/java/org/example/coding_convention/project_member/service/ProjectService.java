package org.example.coding_convention.project_member.service;

import lombok.RequiredArgsConstructor;
import org.example.coding_convention.project_member.model.ProjectMember;
import org.example.coding_convention.project_member.model.ProjectMemberDto;
import org.example.coding_convention.project_member.repository.ProjectMemberRepository;
import org.example.coding_convention.user.model.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectMemberRepository projectRepository;

    public void save(ProjectMemberDto.ProjectMemberReq dto) {
        String status = dto.getStatus();
        projectRepository.save(dto.toEntity(status));
    }
}
