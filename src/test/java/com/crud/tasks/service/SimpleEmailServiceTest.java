package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {
    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void shouldSendEmail(){
        //Given
        Mail mail = new Mail("test@test.pl", "test subject", "this is emal body", "mail@other.com");
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setText(mail.getMessage());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setTo(mail.getRecieverEmail());
        Optional.ofNullable(mail.getToCc()).ifPresent(cc -> mailMessage.setCc(cc));
//        if (mail.getToCc()!=null) {
//            mailMessage.setCc(mail.getToCc());
//        }
        //When
        simpleEmailService.send(mail);
        //Then
        verify(javaMailSender,times(1)).send(mailMessage);

    }


}