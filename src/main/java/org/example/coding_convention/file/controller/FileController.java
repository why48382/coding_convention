package org.example.coding_convention.file.controller;

import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(
            summary = "파일 저장 기능",
            description = "파일 저장시 실행되는 기능"
    )
    @PostMapping("/register")
    public BaseResponse register(@RequestBody FilesDto.Register dto) {
        fileService.save(dto);

        return BaseResponse.success("파일 저장완료");
    }

    @Operation(
            summary = "파일 상세조회 기능",
            description = "특정 파일에 대한 path(name)를 제공"
    )
    @GetMapping("/read")
    public BaseResponse read(Integer idx) {
        FilesDto.FilesRes result = fileService.read(idx);

        return BaseResponse.success(result);

    }


}
