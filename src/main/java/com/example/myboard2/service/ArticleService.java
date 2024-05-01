package com.example.myboard2.service;

import com.example.myboard2.dto.ArticleDto;
import com.example.myboard2.entity.Article;
import com.example.myboard2.repository.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
       private final ArticleRepository articleRepository;
       
       public ArticleService(ArticleRepository articleRepository) {
              this.articleRepository = articleRepository;
       }
       
       public List<ArticleDto> showAll() {
              List<ArticleDto> articleDtoList = new ArrayList<>();
              return articleRepository.findAll().stream()
                     .map(x -> ArticleDto.articleToMakeDto(x))
                     .toList();
       }
       
       public void insertArticle(ArticleDto dto) {
              Article article = ArticleDto.articleDtoToMakeEntity(dto);
              articleRepository.save(article);
       }
       
       
       public ArticleDto getOneArticle(Long id) {
              Article article = articleRepository.findById(id).orElse(null);
              if (article == null) {
                     return null;
              } else {
                     return articleRepository.findById(id)
                            .map(x -> ArticleDto.articleToMakeDto(x))
                            .orElse(null);
              }
       }
       
       public void delete(Long id) {
              articleRepository.deleteById(id);
       }
       
       public void update(ArticleDto dto) {
              Article article = ArticleDto.articleDtoToMakeEntity(dto);
              articleRepository.save(article);
       }
       
       public List<ArticleDto> findAll() {
              return articleRepository.findAll().stream().map(x->ArticleDto.articleToMakeDto(x)).toList();
       }
       
       public Page<Article> pagingList(Pageable pageable){
              return articleRepository.findAll(pageable);
       }
}
