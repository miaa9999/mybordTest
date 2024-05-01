package com.example.myboard2.repository;

import com.example.myboard2.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ArticleRepository extends JpaRepository<Article, Long> {
       
       //테스트 -> 컨트롤 쉬프트 t (articleRepository 글자에서)

}
