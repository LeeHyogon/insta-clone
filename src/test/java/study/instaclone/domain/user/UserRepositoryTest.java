package study.instaclone.domain.user;

import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserRepositoryTest {


    @Autowired
    UserRepository userRepository;

    private User user;
    private String email1="test@test";


//    @After
//    public void tearDown() {
//        userRepository.deleteAll();
//    }


    @Test
    @Transactional
    public void findUserByEmail_성공() {
        //given
        userRepository.save(User.builder()
                .email(email1)
                .name("test")
                .password("asd")
                .phone("123123")
                .title(null)
                .website(null)
                .profileImgUrl(null)
                .build());
        //when
        Optional<User> result= userRepository.findUserByEmail(email1);

        //then

        assertTrue(result.isPresent());
        if(result.isPresent()){
            assertThat(result.get().getEmail()).isEqualTo(email1);
        }

    }

    @Test
    void findUserById() {
    }
}