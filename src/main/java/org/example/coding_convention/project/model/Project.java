package org.example.coding_convention.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
