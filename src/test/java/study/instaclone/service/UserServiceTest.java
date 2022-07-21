package study.instaclone.service;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import study.instaclone.domain.user.User;
import study.instaclone.domain.user.UserRepository;
import study.instaclone.web.dto.user.UserLoginDto;

import javax.transaction.Transactional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user = User.builder()
                .email("test@test")
                .name("test")
                .password(encoder.encode("test1234!"))
                .phone("123123")
                .title(null)
                .website(null)
                .profileImgUrl(null)
                .build();
    }


    public UserLoginDto createUserLoginDto() {
        return UserLoginDto.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .phone(user.getPhone())
                .build();
    }



    @Test
    public void save_성공() throws Exception {
        //given
        UserLoginDto userLoginDto=createUserLoginDto();
        given(userRepository.findUserByEmail(any())).willReturn(null);
        given(userRepository.save(any())).willReturn(user);
        //when
        boolean ret = userService.save(userLoginDto);

        //then
        assertTrue(ret);
    }

    @Test
    public void save_실패() throws Exception {
        //given

        //when

        //then
    }
}