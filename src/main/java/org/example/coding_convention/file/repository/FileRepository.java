package org.example.coding_convention.file.repository;

import org.example.coding_convention.file.model.Files;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface FileRepository extends JpaRepository<Files, Integer> {

}
