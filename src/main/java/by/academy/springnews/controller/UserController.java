package by.academy.springnews.controller;


import by.academy.springnews.model.User;
import by.academy.springnews.service.SecurityService;
import by.academy.springnews.service.ServiceException;
import by.academy.springnews.service.UserService;
import by.academy.springnews.service.impl.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidationService userValidationService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerNewUser(@ModelAttribute("userForm") User user, BindingResult bindingResult, Model model) {
        userValidationService.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        try {
            userService.save(user);
            securityService.autoLogin(user.getUsername(), user.getPassword());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "redirect:/list";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error","Username or password is incorrect");
        }
        if(logout!=null){
            model.addAttribute("message", "Logged out successfully");
        }
        return "login";
    }


}
