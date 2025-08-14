package org.example.coding_convention.file.controller;

import lombok.RequiredArgsConstructor;
import org.example.coding_convention.common.BaseResponse;
import org.example.coding_convention.file.model.FilesDto;
import org.example.coding_convention.file.service.FileService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    @PostMapping("/register")
    public BaseResponse register(@RequestBody FilesDto.Register dto) {
        fileService.save(dto);

        return BaseResponse.success("파일 저장완료");
    }

    @GetMapping("/read")
    public BaseResponse read(Integer idx) {
        FilesDto.FilesRes result = fileService.read(idx);

        return BaseResponse.success(result);

    }


}
