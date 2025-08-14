package org.example.coding_convention.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.coding_convention.chat.model.Chats;
import org.example.coding_convention.file.model.Files;
import org.example.coding_convention.project_member.model.ProjectMember;
import org.example.coding_convention.user.model.User;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;
    private String projectName;
    private String url;
    private String description;

    @Enumerated(EnumType.STRING)
    private Language language;

    public enum Language {
        JAVASCRIPT, JAVA, PYTHON, C
    }

    public static Language projectLanguage(String language) {
        return Language.valueOf(language.toUpperCase());
    }
    // ("creator_id")
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User user;

    @OneToMany(mappedBy = "project")
    private List<Files> fileList;

    @OneToMany(mappedBy = "project")
    private List<ProjectMember> projectMemberList;

    @OneToMany(mappedBy = "project")
    private List<Chats> chatsList;
}
