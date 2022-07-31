package study.instaclone.service;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
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
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@Transactional
public class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    private User user;




    public UserLoginDto createUserLoginDto() {
        return UserLoginDto.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .phone(user.getPhone())
                .build();
    }


    @Test
    public void save_회원가입_성공() throws Exception {
        //given
        user = User.builder()
                .email("test@test")
                .name("test")
                .password("1234")
                .phone("123123")
                .title(null)
                .website(null)
                .profileImgUrl(null)
                .build();

        UserLoginDto userLoginDto=createUserLoginDto();
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