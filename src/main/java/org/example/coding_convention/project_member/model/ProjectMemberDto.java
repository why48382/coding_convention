package org.example.coding_convention.project_member.model;

import lombok.Builder;
import lombok.Getter;
import org.example.coding_convention.project.model.Project;
import org.example.coding_convention.user.model.User;

public class ProjectMemberDto {

    @Getter
    @Builder
    public static class ProjectMemberReq {
        private Integer projectId;
        private Integer userId;
        private String status;

        public ProjectMember toEntity(String status) {
            ProjectMember.Status dtoStatus = ProjectMember.memberStatus(status);
            Project project = Project.builder()
                    .idx(projectId)
                    .build();
            User user = User.builder()
                    .idx(userId)
                    .build();

            return ProjectMember.builder()
                    .project(project)
                    .user(user)
                    .status(dtoStatus)
                    .build();
        }
        public ProjectMember toEntity() {
            Project project = Project.builder()
                    .idx(projectId)
                    .build();
            User user = User.builder()
                    .idx(userId)
                    .build();
            ProjectMember.Status dtoStatus = ProjectMember.memberStatus(status);

            return ProjectMember.builder()
                    .project(project)
                    .user(user)
                    .status(dtoStatus)
                    .build();
        }
    }

    @Getter
    @Builder
    public static class ProjectMemberList {
        private Integer userId;
        private String status;

        public static ProjectMemberList from(ProjectMember entity) {
            return ProjectMemberDto.ProjectMemberList.builder()
                    .userId(entity.getUser().getIdx())
                    .status(entity.getStatus().toString())
                    .build();
        }
    }
}
