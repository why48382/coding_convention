package org.example.coding_convention.user.service;

import lombok.RequiredArgsConstructor;
import org.example.coding_convention.user.model.User;
import org.example.coding_convention.user.model.UserDto;
import org.example.coding_convention.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> result = userRepository.findByEmail(email);

        if(result.isPresent()) {
            User user = result.get();
            return UserDto.AuthUser.from(user);
        }
        return null;
    }


    public User signup(UserDto.Register dto) {
        User user = userRepository.save(dto.toEntity());
        return user;
    }
}
