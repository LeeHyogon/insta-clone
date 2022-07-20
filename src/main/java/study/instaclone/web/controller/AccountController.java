package study.instaclone.web.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import study.instaclone.service.UserService;
import study.instaclone.web.dto.user.UserLoginDto;

@RequiredArgsConstructor
@Controller
public class AccountController {

    private final UserService userService;

    //회원가입
    @PostMapping("/signup")
    public String signup(UserLoginDto userLoginDto) {
        if(userService.save(userLoginDto)) {
            return "redirect:/login";
        }
        else{
            return "redirect:/signup?error";
        }
    }
}
