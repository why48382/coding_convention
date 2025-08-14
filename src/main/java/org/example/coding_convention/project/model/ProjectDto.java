package org.example.coding_convention.project.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.coding_convention.project_member.model.ProjectMember;
import org.example.coding_convention.user.model.User;

import java.util.List;

public class ProjectDto {

    @Getter
    public static class ProjectReq {
        private String projectName;
        private String description;
        private String url;
        private String language;
        private Integer userId;

        public Project toEntity(String url) {
            Project.Language dtoLanguage = Project.projectLanguage(language);
            return Project.builder()
                    .projectName(projectName)
                    .description(description)
                    .url(url)
                    .language(dtoLanguage)
                    .build();
        }
    }


    @Getter
    @Builder
    public static class ProejctRead {
        private Integer idx;
        private String projectName;
        private String language;
        // private ProjectFile projectFile;
         private List<ProjectMember> projectMember;
        // private Chat chat;

        public static ProejctRead from(Project entity) {
            return ProejctRead.builder()
                    .idx(entity.getIdx())
                    .projectName(entity.getProjectName())
                    .language(entity.getLanguage().toString())
                    .projectMember(entity.getProjectMemberList())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class ProjectList {
        private Integer idx;
        private String projectName;
        private String description;
        private String language;
        // private String creator;

        // 유저의 아이디를 기준으로 가져와야 함
        // SELECT * FROM project WHERE user_idx = ?

        public static ProjectList from(Project entity) {
            return ProjectList.builder()
                    .idx(entity.getIdx())
                    .projectName(entity.getProjectName())
                    .description(entity.getDescription())
                    .language(entity.getLanguage().toString())
                    .build();
        }
    }

}
