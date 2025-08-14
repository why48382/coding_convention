package org.example.coding_convention.project.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.coding_convention.file.model.Files;
import org.example.coding_convention.file.model.FilesDto;
import org.example.coding_convention.project_member.model.ProjectMember;
import org.example.coding_convention.project_member.model.ProjectMemberDto;
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

        public Project toEntity(String url, User user) {
            Project.Language dtoLanguage = Project.projectLanguage(language);
            return Project.builder()
                    .projectName(projectName)
                    .description(description)
                    .url(url)
                    .language(dtoLanguage)
                    .user(user)
                    .build();
        }
    }


    @Getter
    @Builder
    public static class ProejctRead {
        private Integer idx;
        private String projectName;
        private String language;
         private List<FilesDto.FilesList> projectFile;
         private List<ProjectMemberDto.ProjectMemberList> projectMember;
        // private Chat chat;

        public static ProejctRead from(Project entity) {
            List<Files> filesList = entity.getFileList();
            List<ProjectMember> projectMemberList = entity.getProjectMemberList();

            return ProejctRead.builder()
                    .idx(entity.getIdx())
                    .projectName(entity.getProjectName())
                    .language(entity.getLanguage().toString())
                    .projectMember(projectMemberList.stream().map(ProjectMemberDto.ProjectMemberList::from).toList())
                    .projectFile(filesList.stream().map(FilesDto.FilesList::from).toList())
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
