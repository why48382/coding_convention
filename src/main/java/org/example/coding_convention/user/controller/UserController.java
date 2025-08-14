package org.example.coding_convention.user.controller;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.example.coding_convention.common.BaseResponse;
import org.example.coding_convention.user.model.UserDto;
import org.example.coding_convention.user.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public BaseResponse signup(@RequestBody UserDto.Register dto) throws MessagingException {
        userService.signup(dto);

        return BaseResponse.success("이메일 인증요망");
    }

    @GetMapping("/verify")
    public BaseResponse verify(String uuid) {
        userService.verify(uuid);

        return BaseResponse.success("이메일 인증완료");
    }

}
