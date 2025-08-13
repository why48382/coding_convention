package org.example.coding_convention.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.coding_convention.common.BaseResponse;
import org.example.coding_convention.user.model.UserDto;
import org.example.coding_convention.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public BaseResponse signup(@RequestBody UserDto.Register dto) {
        userService.signup(dto);

        return BaseResponse.success("등록완료");
    }
}
