package org.example.coding_convention.project_member.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.coding_convention.project.model.Project;
import org.example.coding_convention.user.model.User;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        USER, ADMIN
    }

    public static Status memberStatus(String status) {
        return Status.valueOf(status.toUpperCase());
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
