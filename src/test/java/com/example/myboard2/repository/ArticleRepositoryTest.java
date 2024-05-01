package com.example.myboard2.repository;

import com.example.myboard2.entity.Article;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
//@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ArticleRepositoryTest {
       
       @Autowired
       ArticleRepository articleRepository;
       
       @Test
//       @Transactional
       @DisplayName("모든 자료 검색 성공")
       void findAllTest_class() {
              
              //Given
              Article article1 = new Article(1L, "가가가", "1111");
              Article article2 = new Article(2L, "나나나", "2222");
              Article article3 = new Article(3L, "다다다", "3333");
              List<Article> expectList = new ArrayList<>();
              expectList.add(article1);
              expectList.add(article2);
              expectList.add(article3);
              //When
              List<Article> resultList = articleRepository.findAll();
              //Then
              Assertions.assertThat(resultList.toString()).isEqualTo(expectList.toString());
              //assertJ
              
       }
       
       
       @Test
       @DisplayName("모든 자료 검색 실패")
       void findAllTest_false() {
              
              //Given
              Article article1 = new Article(1L, "라라라", "1111");
              Article article2 = new Article(2L, "나나나", "2222");
              Article article3 = new Article(3L, "다다다", "3333");
              List<Article> expectList = new ArrayList<>();
              expectList.add(article1);
              expectList.add(article2);
              expectList.add(article3);
              //When
              List<Article> resultList = articleRepository.findAll();
              //Then
              Assertions.assertThat(resultList.toString()).isNotEqualTo(expectList.toString());
              //assertJ
              
       }
       
       @Test
       @DisplayName("모든 자료의 수")
       @Order(value = 3)
       void findAllTest_count() {
              //Given
              int expectCount = 3;
              //When
              int actualCount = articleRepository.findAll().size();
              //Then
              Assertions.assertThat(actualCount).isEqualTo(expectCount);
       }
       
       @Test
       @DisplayName("자료 입력 테스트 성공")
       @Order(value = 2)
       void insertTest() {
              //Given
              Article expectArticle = new Article(4L, "라라라", "4444");
              //When
              Article newArticle = new Article(null, "라라라", "4444");
              articleRepository.save(newArticle);
              //Then
              Article acuralArticle = articleRepository.findById(4L).orElse(null);
              Assertions.assertThat(acuralArticle.toString()).isEqualTo(expectArticle.toString());
       }
       
       @Test
       @DisplayName("자료삭제 테스트")
       @Order(value = 1)
       void deleteTest() {
              //Given
              Long deleteId = 4L;
              //When
              articleRepository.deleteById(deleteId);
              //Then
              Article actualResult = articleRepository.findById(deleteId).orElse(null);
              Assertions.assertThat(actualResult).isEqualTo(null);
       }
       
       @Test
       @DisplayName("자료수정 테스트")
       void updateTest() {
              //Given
//              Long deleteId = 1L;
              Article expectArticle = new Article(1L, "타이틀수정", "내용수정");
              //When
              articleRepository.save(expectArticle);
              Article actualArticle = articleRepository.findById(1L).orElse(null);
              //Then
              Assertions.assertThat(actualArticle.toString()).isEqualTo(expectArticle.toString());
       }
       
}