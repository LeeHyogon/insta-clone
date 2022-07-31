package study.instaclone.domain.user;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserRepositoryTest {


    @Autowired
    UserRepository userRepository;

    private String email1="test@test";




    @Test
    @Transactional
    public void findUserByEmail_성공() {
        User user=User.builder()
                .email("test@test")
                .name("test")
                .password("1234")
                .phone("123123")
                .title(null).website(null).profileImgUrl(null)
                .build();
        //given
        userRepository.save(user);

        //when
        User result = userRepository.findUserByEmail("test@test");

        //then
        assertThat(result.getEmail()).isEqualTo(user.getEmail());
    }


}