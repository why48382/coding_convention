package org.example.coding_convention.chat.controller;

import lombok.RequiredArgsConstructor;
import org.example.coding_convention.chat.model.ChatsDto;
import org.example.coding_convention.chat.service.ChatService;
import org.example.coding_convention.common.BaseResponse;
import org.example.coding_convention.user.model.User;
import org.example.coding_convention.user.model.UserDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    @PostMapping("/register")
    public BaseResponse register(@RequestBody ChatsDto.Register dto, @AuthenticationPrincipal UserDto.AuthUser authUser) {
        chatService.save(dto ,authUser);

        return BaseResponse.success("파일 저장완료");
    }

}
