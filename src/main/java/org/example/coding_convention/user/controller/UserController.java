package org.example.coding_convention.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.example.coding_convention.common.BaseResponse;
import org.example.coding_convention.user.model.UserDto;
import org.example.coding_convention.user.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "유저관련기능")
public class UserController {
    private final UserService userService;

    @Operation(
            summary = "회원가입기능",
            description = "회원가입할 때 실행되는 기능"
    )
    @PostMapping("/signup")
    public BaseResponse signup(@RequestBody UserDto.Register dto) throws MessagingException {
        userService.signup(dto);

        return BaseResponse.success("이메일 인증요망");
    }

    @Operation(
            summary = "이메일 인증기능",
            description = "이메일 인증시 실행되는 기능"
    )
    @GetMapping("/verify")
    public BaseResponse verify(String uuid) {
        userService.verify(uuid);

        return BaseResponse.success("이메일 인증완료");
    }

}
