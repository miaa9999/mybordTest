package com.example.myboard2.controller;

import com.example.myboard2.dto.UserCreateForm;
import com.example.myboard2.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserAccountController {
       
       private final UserService userService;
       
       public UserAccountController(UserService userService) {
              this.userService = userService;
       }
       
       @GetMapping("signup")
       private String signup(UserCreateForm userCreateForm){
              return "signup";
       }

       @PostMapping("signup")
       private String signup(@Valid UserCreateForm userCreateForm,
                                 BindingResult bindingResult){
              if (bindingResult.hasErrors()){
                     return "signup";
              }
              
              if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())){
                     bindingResult.rejectValue
                            ("password2", "passwordIncorrect", "비밀번호가 일치하지 않습니다.");
                     return "signup";
                     
              }
              userService.createUser(userCreateForm);
              
              return "redirect:/";
       }
       
       @GetMapping("login")
       private String login(UserCreateForm userCreateForm){
              return "login";
       }
}
