package org.example.coding_convention.project.service;

import lombok.RequiredArgsConstructor;
import org.example.coding_convention.project.model.Project;
import org.example.coding_convention.project.model.ProjectDto;
import org.example.coding_convention.project.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public void save(ProjectDto.ProjectReq dto) {
        // url 생성 로직 아니면 dto 받자마자 dto에서 만들어도 될지도
        String url = "test";
        projectRepository.save(dto.toEntity(url));
        // 저장이 진짜 되었는지 검증하고 싶으면 엔티티 반환해주면 됨
    }

    public ProjectDto.ProejctRead read(Integer idx) {
        Optional<Project> result = projectRepository.findById(idx);
        if (result.isPresent()) {
            Project project = result.get();
            return ProjectDto.ProejctRead.from(project);
        }
        return null;
    }

//    public List<ProjectDto.ProejctRead> list() {
//        List<Project> result = projectRepository.findAll();
//        return result.stream().map(ProjectDto.ProjectList::from).toList();
//    }
}
