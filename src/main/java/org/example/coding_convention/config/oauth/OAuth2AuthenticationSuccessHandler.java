package org.example.coding_convention.config.oauth;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.coding_convention.common.BaseResponse;
import org.example.coding_convention.user.model.UserDto;
import org.example.coding_convention.utils.JwtUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("LoginFilter 성공 로직.");
        UserDto.AuthUser authUser = (UserDto.AuthUser) authentication.getPrincipal();

        String jwt = JwtUtil.generateToken(authUser.getEmail(), authUser.getIdx());

        if (jwt != null) {
            Cookie cookie = new Cookie("SJB_AT", jwt);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);
            response.getWriter().write(new ObjectMapper().writeValueAsString(UserDto.LoginRes.from(authUser)));
        }
    }
}
