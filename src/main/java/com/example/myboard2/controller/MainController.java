package com.example.myboard2.controller;

import com.example.myboard2.dto.ArticleDto;
import com.example.myboard2.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {
       
       private final ArticleService articleService;
       
       public MainController(ArticleService articleService) {
              this.articleService = articleService;
       }
       
       @GetMapping("/")
       public String main (Model model){
              List<ArticleDto> list = articleService.findAll();
              model.addAttribute("dto",list);
              return "/articles/show_all";
       }
       
       
}
