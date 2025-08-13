package org.example.coding_convention.user.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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
                    .enabled(false)
                    .build();

            return entity;
        }
    }

    @Builder
    @Getter
    public static class AuthUser implements UserDetails, OAuth2User {
        private Integer idx;
        private String email;
        private String password;
        private String nickname;
        private Boolean enabled;
        private Map<String, Object> attributes;

        @Override
        public String getName() {
            return (nickname != null && !nickname.isBlank()) ? nickname : email;
        }

        @Override
        public boolean isEnabled() {return enabled;}

        @Override
        public Map<String, Object> getAttributes() {
            return attributes;
        }

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
                    .enabled(entity.getEnabled())
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




