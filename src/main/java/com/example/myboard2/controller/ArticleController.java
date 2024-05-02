package com.example.myboard2.controller;

import com.example.myboard2.entity.Article;
import com.example.myboard2.service.PaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import com.example.myboard2.dto.ArticleDto;
import com.example.myboard2.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleController {
       private final ArticleService articleService;
       @Autowired
     PaginationService paginationService;
       public ArticleController(ArticleService articleService, PaginationService paginationService) {
              this.articleService = articleService;

       }
       

       
       @GetMapping("/paging")
       public String paging (Model model,
                             @PageableDefault(page = 0, size = 10, sort = "id"
                             ,direction = Sort.Direction.DESC)Pageable pageable){
              //넘겨온 페이지 번호를 리스트 받아오기
              Page<Article> paging = articleService.pagingList(pageable);
              model.addAttribute("paging", paging);
              //페이지 블럭 처리(1, 2, 3, 4, 5)
              int totalPage = paging.getTotalPages();
              List<Integer> paginationBarNum = paginationService.getPaginationBarNum(pageable.getPageNumber(), totalPage);
              model.addAttribute("pageBarNum", paginationBarNum);
              return "/articles/show_all_list";
       }
       
       @GetMapping("/insert")
       public String insertView(Model model){
              model.addAttribute("articleDto", new ArticleDto());
              return "/articles/new";
       }
       @PostMapping("insert")
       public String insertArticle(@ModelAttribute("articleDto") ArticleDto dto){
              articleService.insertArticle(dto);
              return "redirect:/";
       }
       
       @GetMapping("showOne")
       public String detailArticle(@RequestParam("selectId") Long id,
                                   Model model){
              ArticleDto articleDto = articleService.getOneArticle(id);
              model.addAttribute("dto", articleDto);
              return "/articles/showOne";
       }
       
       @PostMapping("/delete")
       public String deleteArticle(@RequestParam("deleteId") Long id,
                                   RedirectAttributes redirectAttributes){
              //삭제할 아이디 존재여부 확인
              ArticleDto articleDto = articleService.getOneArticle(id);
              //대상 엔티티가 삭제하면 메세지 전송!
              if(articleDto !=null){
                     articleService.delete(id);
                     redirectAttributes.addFlashAttribute("msg", "정상적으로 삭제되었습니다");
              }
              return "redirect:/";
       }
       
       @GetMapping("/{updateId}")
       public String updateArticle(@RequestParam("updateId") Long id,
                                   Model model){
              ArticleDto articleDto = articleService.getOneArticle(id);
              model.addAttribute("articleDto", articleDto);
              return "/articles/updateForm";
       }
       
       @PostMapping("/update")
       public String update(@ModelAttribute("articleDto") ArticleDto dto){
              articleService.update(dto);
              return "redirect:/";
       }
       
       
       
}
