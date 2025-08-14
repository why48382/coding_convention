package org.example.coding_convention.project.repository;

import org.example.coding_convention.project.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findByUser_Idx(Integer userIdx);
}
