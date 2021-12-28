package by.tms.springboot.controller;

import by.tms.springboot.dto.GetUserDto;
import by.tms.springboot.dto.SaveUserDto;
import by.tms.springboot.entity.User;
import by.tms.springboot.service.UserService;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("newUser", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("newUser") @Valid SaveUserDto saveUserDto, BindingResult bindingResult,
                               HttpSession session, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("newUser", saveUserDto);
            return "registration";
        }

        if (userService.existByUsername(saveUserDto.getUsername()) == null) {
            User user = new User(saveUserDto.getName(), saveUserDto.getUsername(), saveUserDto.getPassword());
            userService.save(user);
            return "redirect: authorization";
        } else {
            model.addAttribute("alert", "Username is already exist");
        }
        return "registration";
    }

    @GetMapping("/authorization")
    public String authorization(Model model) {
        model.addAttribute("user", new User());
        return "authorization";
    }

    @PostMapping("/authorization")
    public String authorization(@ModelAttribute("user") @Valid GetUserDto getUserDto, BindingResult bindingResult,
                                HttpSession session, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", getUserDto);
            return "registration";
        }

        User memoryUser = userService.existByUsername(getUserDto.getUsername());
        if(memoryUser != null) {
            if (memoryUser.getPassword().equals(getUserDto.getPassword())) {
                session.setAttribute("user", memoryUser);
                return "redirect: /";
            } else {
                model.addAttribute("alert", "Password input incorrectly");
            }
        } else {
            model.addAttribute("alert", "User not found");
        }
        return "authorization";
    }

    @GetMapping("/logOut")
    public String logOut(HttpSession session) {
        session.invalidate();
        return "redirect: /";
    }
}
