package org.example.coding_convention.user.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDto {

    @Getter
    public static class Register {
        private String email;
        private String password;
        private String nickname;

        public User toEntity() {
            User entity = User.builder()
                    .email(email)
                    .password(password)
                    .nickname(nickname)
                    .build();

            return entity;
        }
    }

    @Builder
    @Getter
    public static class AuthUser implements UserDetails {
        private Integer idx;
        private String email;
        private String password;
        private String nickname;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }

        @Override
        public String getPassword() {
            return "{noop}" + password;
        }

        @Override
        public String getUsername() {
            return email;
        }

        public static AuthUser from(User entity) {
            AuthUser dto = AuthUser.builder()
                    .idx(entity.getIdx())
                    .email(entity.getEmail())
                    .password(entity.getPassword())
                    .nickname(entity.getNickname())
                    .build();

            return dto;
        }
    }

    @Getter
    public static class Login {
        private String email;
        private String password;
    }

    @Getter
    @Builder
    public static class LoginRes {
        private Integer idx;
        private String email;
        private String nickname;

        public static LoginRes from(AuthUser authUser) {
            LoginRes dto = LoginRes.builder()
                    .idx(authUser.getIdx())
                    .email(authUser.getEmail())
                    .nickname(authUser.getNickname())
                    .build();

            return dto;
        }
    }
}




