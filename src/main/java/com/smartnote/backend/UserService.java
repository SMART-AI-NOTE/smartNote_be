package com.smartnote.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.IllformedLocaleException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void signup(SignupRequestDto requestDto) {
        String email = requestDto.getEmail();

        if(userRepository.findByEmail(email) != null) {
            throw new IllformedLocaleException("이미 존재하는 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        User user = new User();
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setNickname(requestDto.getNickname());

        userRepository.save(user);
    }
}
