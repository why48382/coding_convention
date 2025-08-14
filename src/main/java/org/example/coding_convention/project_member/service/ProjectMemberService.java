package org.example.coding_convention.project_member.service;

import lombok.RequiredArgsConstructor;
import org.example.coding_convention.project_member.model.ProjectMemberDto;
import org.example.coding_convention.project_member.repository.ProjectMemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectMemberService {
    private final ProjectMemberRepository projectRepository;

    public void save(ProjectMemberDto.ProjectMemberReq dto) {
        String status = dto.getStatus();
        projectRepository.save(dto.toEntity(status));
    }
}
