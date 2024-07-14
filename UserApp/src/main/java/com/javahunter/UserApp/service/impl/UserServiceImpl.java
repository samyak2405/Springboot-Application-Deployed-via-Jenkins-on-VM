package com.javahunter.UserApp.service.impl;

import com.javahunter.UserApp.entity.User;
import com.javahunter.UserApp.repository.UserRepository;
import com.javahunter.UserApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
