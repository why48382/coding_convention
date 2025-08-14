package org.example.coding_convention.file.model;

import lombok.Builder;
import lombok.Getter;
import org.example.coding_convention.project.model.Project;

import java.util.List;

public class FilesDto {

    @Getter
    public static class Register {

        private Integer idx;
        private String name;

        public Files toEntity(String filetype) {
            Project project = Project.builder()
                    .idx(idx)
                    .build();

            Files entity = Files.builder()
                    .project(project)
                    .name(this.name)
                    .type(Files.setFileType(filetype))
                    .build();

            return entity;
        }
    }
    @Getter
    @Builder
    public static class FilesList {
        private Integer idx;
        private String name;

        public static FilesDto.FilesList from(Files filesEntity) {
            return FilesDto.FilesList.builder()
                    .idx(filesEntity.getIdx())
                    .name(filesEntity.getName())
                    .build();
        }
    }

}
