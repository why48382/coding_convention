package org.example.coding_convention.file.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.coding_convention.project.model.Project;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Files {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column(name = "file_path", length = 500)
    private String path;

    @Column(name = "file_name", length = 500)
    private String name;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private FileType type;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime saveTimeAt;

    @ManyToOne
    @JoinColumn(name = "project_idx")
    Project project;

    public static FileType setFileType(String type) {
        return FileType.valueOf(type);
    }


    public enum FileType {
        DIRECTORY,    // 필요한 타입으로 변경
        FILE
    }

}
