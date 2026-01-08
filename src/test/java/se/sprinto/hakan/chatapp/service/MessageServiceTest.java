package se.sprinto.hakan.chatapp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sprinto.hakan.chatapp.model.Message;
import se.sprinto.hakan.chatapp.model.User;
import se.sprinto.hakan.chatapp.repository.MessageRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageService messageService;

    @Test
    void saveTest(){
        User user = new User();
        user.setId(1L);

        Message msg = new Message(user,"Hello Reader", LocalDateTime.now());
        messageService.save(msg);

        verify(messageRepository).save(msg);
    }

    @Test
    void getMessages(){
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        Message  msg = new Message(user,"Tjabba!", LocalDateTime.now());
        Message msg2 = new Message(user,"Ses så länge!", LocalDateTime.now());

        List<Message> messages = List.of(msg,msg2);
        when(messageRepository.findByUserId(userId)).thenReturn(messages);
        List<Message> result = messageService.getMessages(userId);
        assertEquals(2,result.size());
        assertEquals("Tjabba!",result.get(0).getText());
        verify(messageRepository).findByUserId(userId);

    }
}
