package com.example.myboard2.dto;

import com.example.myboard2.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {
       
       private Long id;
       
       private String title;
       
       private String content;
       
       public static ArticleDto articleToMakeDto (Article article){
              
              return new ArticleDto(
                     article.getId(),
                     article.getTitle(),
                     article.getContent()
              );
       }
       
       public static Article articleDtoToMakeEntity (ArticleDto dto){
              Article article = new Article();
              article.setId(dto.getId());
              article.setTitle(dto.getTitle());
              article.setContent(dto.getContent());
              
              return article;
       }
}
