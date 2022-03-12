package uz.abdukarimov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.abdukarimov.exceptions.NotFoundException;
import uz.abdukarimov.models.AuthLogin;
import uz.abdukarimov.services.LoginService;

@Controller
@RequestMapping("/auth/*")
public class AuthController {

    private final LoginService loginService;

    public AuthController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    private String loginPage() {
        return "auth/login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    private String login(AuthLogin login) {

        if(loginService.checkUser(login)){
            return "redirect:/book/list/";
        };
        throw new NotFoundException("User not found");
    }

}
