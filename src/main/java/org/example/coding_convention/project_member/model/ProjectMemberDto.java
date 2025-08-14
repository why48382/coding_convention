package org.example.coding_convention.project_member.model;

import lombok.Builder;
import lombok.Getter;
import org.example.coding_convention.project.model.Project;
import org.example.coding_convention.user.model.User;

public class ProjectMemberDto {

    @Getter
    public static class ProjectMemberReq {
        private Integer userId;
        private String status;

        public ProjectMember toEntity(String status) {
            ProjectMember.Status dtoStatus = ProjectMember.memberStatus(status);
            return ProjectMember.builder()
                    .project()
                    .idx(userId)
                    .status(dtoStatus)
                    .build();
        }
        public ProjectMember toEntity(Integer userId, Integer projectId, String status) {
            ProjectMember.Status dtoStatus = ProjectMember.memberStatus(status);
            Project projectIdx = Project.builder()
                    .idx(projectId)
                    .build();
            User userIdx = User.builder()
                    .idx(userId)
                    .build();

            return ProjectMember.builder()
                    .project(projectIdx)
                    .user(userIdx)
                    .status(dtoStatus)
                    .build();
        }
    }
}
