package org.example.coding_convention.file.controller;

import lombok.RequiredArgsConstructor;
import org.example.coding_convention.common.BaseResponse;
import org.example.coding_convention.file.model.FilesDto;
import org.example.coding_convention.file.service.FileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    @GetMapping("/register")
    public BaseResponse register(FilesDto.Register dto) {
        fileService.save(dto);

        return BaseResponse.success("파일 저장완료");
    }


}
