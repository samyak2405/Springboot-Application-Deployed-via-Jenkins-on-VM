package com.javahunter.UserApp.service;

import com.javahunter.UserApp.entity.User;
import com.javahunter.UserApp.repository.UserRepository;
import com.javahunter.UserApp.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setName("Raghav raj");
        user.setPassword("12345");
    }

    @Test
    void testSaveUser() {
        Mockito.when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.saveUser(user);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isEqualTo(1L);
        assertThat(savedUser.getName()).isEqualTo("Raghav raj");
        assertThat(savedUser.getPassword()).isEqualTo("12345");

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }
}