package org.example.coding_convention.file.model;

import lombok.Getter;
import org.example.coding_convention.project.model.Project;

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


}
