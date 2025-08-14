package org.example.coding_convention.project.service;

import lombok.RequiredArgsConstructor;
import org.example.coding_convention.project.model.Project;
import org.example.coding_convention.project.model.ProjectDto;
import org.example.coding_convention.project.repository.ProjectRepository;
import org.example.coding_convention.project_member.model.ProjectMember;
import org.example.coding_convention.project_member.repository.ProjectMemberRepository;
import org.example.coding_convention.user.model.User;
import org.example.coding_convention.user.model.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;

    public void save(ProjectDto.ProjectReq dto, UserDto.AuthUser authUser) {
        // url 생성 로직 아니면 dto 받자마자 dto에서 만들어도 될지도
        String url = "test";
        Project project = projectRepository.save(dto.toEntity(url));
        Integer userId = authUser.getIdx();
        Integer projectId = project.getIdx();
        String status = "ADMIN";

        projectMemberRepository.save(userId, projectId, status);
        // 저장이 진짜 되었는지 검증하고 싶으면 엔티티 반환해주면 됨
    }

    public ProjectDto.ProejctRead read(Integer idx) {
        Optional<Project> result = projectRepository.findById(idx);
//        List<ProjectMember> projectMembers = projectMemberRepository.findByProject_Idx(idx);
        if (result.isPresent()) {
            Project project = result.get();
            return ProjectDto.ProejctRead.from(project);
        }
        return null;
    }

    public List<ProjectDto.ProjectList> list(UserDto.AuthUser authUser) {
        Integer userId = (Integer) authUser.getIdx();
        List<Project> result = projectRepository.findByUser_Idx(userId);
//        List<ProjectMember> projectMembers = projectMemberRepository.findByUser_Idx(userId);
        //TODO 프로젝트 맴버도 받아서 다른 이름으로 데이터 전송하게 하기
        return result.stream().map(ProjectDto.ProjectList::from).toList();
    }
}
