package study.instaclone.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import study.instaclone.domain.user.User;
import study.instaclone.domain.user.UserRepository;
import study.instaclone.web.dto.user.UserLoginDto;

import javax.transaction.Transactional;

//초기화 되지 않은 final 필드와 @NonNull 어노테이션이 붙은 필드에 대한 생성자 생성
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;


    @Transactional
    public boolean save(UserLoginDto userLoginDto) {

        //Optional사용취소. 이메일반환값 존재하지 않으면 false반환.
        if(userRepository.findUserByEmail(userLoginDto.getEmail())!=null) return false;

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userRepository.save(User.builder()
                .email(userLoginDto.getEmail())
                .password(encoder.encode(userLoginDto.getPassword()))
                .phone(userLoginDto.getPhone())
                .name(userLoginDto.getName())
                .title(null)
                .website(null)
                .profileImgUrl("/img/default_profile.jpg")
                .build());
        return true;
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException{
        User user=userRepository.findUserByEmail(email);

        if(user==null) return null;

        return user;
    }

}
