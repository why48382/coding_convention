package org.example.coding_convention.file.service;

import lombok.RequiredArgsConstructor;
import org.example.coding_convention.file.model.Files;
import org.example.coding_convention.file.model.FilesDto;
import org.example.coding_convention.file.repository.FileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    public void save(FilesDto.Register dto){

        String dtoName = dto.getName();
        if (dtoName.contains(".")) {
            String fileType = "FILE";
            fileRepository.save(dto.toEntity(fileType));
        } else {
            String fileType = "DIRECTORY";
            fileRepository.save(dto.toEntity(fileType));
        }


    }

    public FilesDto.FilesRes read(Integer idx) {
        Optional<Files> result = fileRepository.findById(idx);
        if(result.isPresent()) {

            Files entity = result.get();
            return FilesDto.FilesRes.from(entity);
        }
        return null;
    }

}
