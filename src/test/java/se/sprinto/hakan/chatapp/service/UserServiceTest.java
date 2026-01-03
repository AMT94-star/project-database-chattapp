package se.sprinto.hakan.chatapp.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sprinto.hakan.chatapp.model.User;
import se.sprinto.hakan.chatapp.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void loginTest() {
        User user = new User();
        user.setUsername("Asli");
        user.setPassword("123");

        when(userRepository.findByUsernameAndPassword("Asli","123"))
                .thenReturn(user);
        User result = userService.login("Asli","123");

        assertNotNull(result);
        assertEquals(user,result);

        verify(userRepository).findByUsernameAndPassword("Sonny","123");
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void registerTest(){
        User newUser = new User();
        newUser.setUsername("Anna");
        newUser.setPassword("321");

        when(userRepository.save(newUser)).thenReturn(newUser);
        User result = userService.register(newUser);
        assertEquals(newUser,result);
        verify(userRepository).findByUsernameAndPassword("Anna","321");
        verifyNoMoreInteractions(userRepository);
    }
}
