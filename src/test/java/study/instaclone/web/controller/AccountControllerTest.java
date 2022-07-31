package study.instaclone.web.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import study.instaclone.domain.user.User;
import study.instaclone.domain.user.UserRepository;

import javax.transaction.Transactional;

import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Transactional
    public void login_성공() throws Exception {
        String username="test@test";
        String password="1234";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User newUser = User.builder()
                .email(username)
                .name("test")
                .password(password)
                .phone("123123")
                .title(null)
                .website(null)
                .profileImgUrl(null)
                .build();
        userRepository.save(newUser);

        //https://galid1.tistory.com/768 ,자동으로 유저를 로그인페이지로 리다이렉션시키기 때문에 ,테스트진행어렵.
        //https://taesan94.tistory.com/119 , There is no PasswordEncoder mapped for the id "null"에러.
        mockMvc.perform(formLogin("/loginForm").user(username).password(password))
                .andDo(print())
                .andExpect(redirectedUrl("/user/story"));
    }

    @Test
    @Transactional
    public void login_실패() throws Exception {
        String username="test@test";
        String password="1234";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User newUser = User.builder()
                .email(username)
                .name("test")
                .password(password)
                .phone("123123")
                .title(null)
                .website(null)
                .profileImgUrl(null)
                .build();
        userRepository.save(newUser);

          mockMvc.perform(formLogin("/loginForm").user(username).password("123214"))
                .andDo(print())
                .andExpect(redirectedUrl("/login?error"));
    }

}