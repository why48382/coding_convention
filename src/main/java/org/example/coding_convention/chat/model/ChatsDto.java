package org.example.coding_convention.chat.model;

import lombok.Builder;
import lombok.Getter;
import org.example.coding_convention.file.model.FilesDto;
import org.example.coding_convention.project.model.Project;
import org.example.coding_convention.user.model.User;
import org.example.coding_convention.user.model.UserDto;

public class ChatsDto {

    @Getter
    @Builder
    public static class ChatList {
        private Integer idx;
        private String username;
        private String message;

        public static ChatsDto.ChatList from(Chats chatsEntity) {
            return ChatList.builder()
                    .idx(chatsEntity.getId())
                    .username(chatsEntity.getUser().getNickname())
                    .message(chatsEntity.getMessage())
                    .build();
        }
    }

    @Getter
    public static class Register {
        private Integer projectId;
        private Integer userId;
        private String message;

        public Chats toEntity(UserDto.AuthUser authUser) {
            Project prrojectEntity = Project.builder()
                    .idx(projectId)
                    .build();
            User userEntity = User.builder()
                    .idx(authUser.getIdx())
                    .build();
            return Chats.builder()
                    .project(prrojectEntity)
                    .user(userEntity)
                    .message(message)
                    .build();
        }


    }


}
