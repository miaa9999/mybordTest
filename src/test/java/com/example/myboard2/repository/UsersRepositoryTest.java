package com.example.myboard2.repository;

import com.example.myboard2.entity.Users;
import com.example.myboard2.vo.Gender;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class UsersRepositoryTest {
       
       @Autowired
       UsersRepository usersRepository;
       
       @Test
       @DisplayName("findByName 테스트")
       void findByNameTest() {
              //이름찾기
              String findName = "Marco";
              usersRepository.findByName(findName).forEach(users -> System.out.println(users));
       }
       
       @Test
       @DisplayName("findByTop3ByLike 테스트")
       void findByTop3ByLikeTest() {
              //pink 색상 데이터 중 상위 3개 데이터만 가져오기
              String color = "Pink";
              usersRepository.findTop3ByLikeColor(color).forEach(users -> System.out.println(users));
              
       }
       
       @Test
       @DisplayName("findByGenderAndLikeColor 테스트")
       void findByGenderAndLikeColorTest() {
              //pink 색상 데이터 중 상위 3개 데이터만 가져오기
              String color = "Pink";
              usersRepository.findByGenderAndLikeColor(Gender.Male, color).forEach(users -> System.out.println(users));
       }
       
       @Test
       @DisplayName("findByCreateAdAfter 테스트")
       void findByCreateAdAfter() {
              usersRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(10L)).forEach(users -> System.out.println(users));
       }
       
       @Test
       @DisplayName("findByCreatedAtGreaterThanEqual 테스트")
       void findByCreatedAtGreaterThanEqual() {
              usersRepository.findByCreatedAtGreaterThanEqual(LocalDateTime.now().minusDays(15L)).forEach(users -> System.out.println(users));
       }
       
       @Test
       @DisplayName("findByCreatedAtBetween 테스트")
       void findByCreatedAtBetween() {
              usersRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(31), LocalDateTime.now()).forEach(users -> System.out.println(users));
              usersRepository.findByIdBetween(1L, 5L).forEach(users -> System.out.println(users));
       }
       
       @Test
       @DisplayName("findByUpdatedAtIsNotNull 테스트")
       void findByUpdatedAtIsNotNull() {
              System.out.println("=== in not null count : " + usersRepository.findByUpdatedAtIsNotNull().stream().count());
       }
       
       @Test
       @DisplayName("findByLikeColorIn 테스트")
       void findByLikeColorIn() {
              usersRepository.findByLikeColorIn(Lists.newArrayList("Red", "Pink"))
                     .forEach(users -> System.out.println(users));
       }
       
       
       @Test
       @DisplayName("문자열 관련 쿼리 테스트 문제 발생지점")
       void stringSearechTest() {
              usersRepository.findByNameStartingWith("S")
                     .forEach(users -> System.out.println("S로 시작 : " + users));
              usersRepository.findByNameEndingWith("t")
                     .forEach(users -> System.out.println("t로 끝 : " + users));
              usersRepository.findByNameContains("k")
                     .forEach(users -> System.out.println("k 포함 : " + users));
              System.out.println("contain의 수 : " + usersRepository.findByNameContains("k").stream().count());
              usersRepository.findByNameLike("%k%")
                     .forEach(users -> System.out.println("%k% 구문 : " + users));
              System.out.println("Like 의 수 : " + usersRepository.findByNameLike("%k%").stream().count());
              
       }
       
       @Test
       @DisplayName("findByLikeColorIn 테스트")
       void findByColorAndSort() {
              usersRepository.findByLikeColor("Orange", Sort.by(Sort.Order.asc("gender"), Sort.Order.desc("createdAt")))
                     .forEach(users -> System.out.println(users));
       }
       
       @Test
       @DisplayName("Users 테이블 입력_테스트")
//       @Transactional
       void inputUsers() {
              Users user = Users.builder()
                     .name("홍길동")
                     .email("test@test.com")
                     .gender(Gender.Male)
                     .likeColor("Yellow")
//                     .createdAt(LocalDateTime.now())
//                     .updatedAt(LocalDateTime.now())
                     .build();
              
              usersRepository.save(user);
       }
       
       @Test
       @DisplayName("Users 테이블 수정_테스트")
       void usersUpdate() {
              Users user = Users.builder()
                     .id(1L)
                     .name("홍길순")
                     .email("test22@test.com")
                     .gender(Gender.Female)
                     .likeColor("Red")
//                     .createdAt(LocalDateTime.now().plusDays(1L)) // 내일 날짜
//                     .updatedAt(LocalDateTime.now().plusDays(1L))
                     .build();
              
              usersRepository.save(user);
       }
       
       //전체 페이징
       @Test
       @DisplayName("페이징 테스트")
       void pagingTest() {
              System.out.println("페이지 = 0, 페이지당 리스트 수 : 5");
              usersRepository.findAll(
                     PageRequest.of(0, 5, Sort.by(Sort.Order.desc("id")))
              ).forEach(users -> System.out.println(users));
              System.out.println("페이지 = 1, 페이지당 리스트 수 : 5");
              usersRepository.findAll(
                     PageRequest.of(1, 5, Sort.by(Sort.Order.desc("id")))
              ).forEach(users -> System.out.println(users));
              System.out.println("페이지 = 0, 페이지당 리스트 수 : 5");
              usersRepository.findAll(
                     PageRequest.of(2, 5, Sort.by(Sort.Order.desc("id")))
              ).forEach(users -> System.out.println(users));
              System.out.println("페이지 = 0, 페이지당 리스트 수 : 5");
              usersRepository.findAll(
                     PageRequest.of(3, 5, Sort.by(Sort.Order.desc("id")))
              ).forEach(users -> System.out.println(users));
       }
       
       @Test
       @DisplayName("200번 아이디 이상 페이징 테스트")
       void pagingGreaterThan200Test() {
              Pageable pageable = PageRequest.of(30, 10);
              Page<Users> result = usersRepository.findByIdGreaterThanEqualOrderByIdDesc(200L, pageable);
              
              result.getContent().forEach(users -> System.out.println(users));
              //총 페이지 출력
              System.out.println("Total page : " + result.getTotalPages());
              
              //전체 데이터 개수
              System.out.println("Total contents count : " + result.getTotalElements());
              
              //현재 페이지 번호
              System.out.println("Current page number : " + result.getNumber());
              
              //다음 페이지 존재 여부
              System.out.println("next page : " + result.hasNext());
              
              //이전 페이지 존재 여부 -> 시작 페이지 인지 여부
              System.out.println("시작페이지 여부 : " + result.isFirst());
       }
       
       @Test
       void quest1() {
              List<Users> usersList = usersRepository.findByNameLikeOrNameLike("%m%", "%w%");
              for (Users user : usersList) {
                     if (user.getGender().equals(Gender.Female)) {
                            System.out.println(user);
                     }
              }
              
              usersRepository.findByNameLikeAndGenderOrNameLikeAndGender
                            ("%m%",Gender.Female,"%w%",Gender.Female)
                     .forEach(users -> System.out.println("방법2 : " + users));
       }
       
       @Test
       void quest2() {
              System.out.println(
                     "net 이 들어간 이메일의 개수 : " +
                            usersRepository.findByEmailContains("net").stream().count()
              );
       }
       
       @Test
       void quest3() {
              //두달전
              usersRepository.findByUpdatedAtBetweenAndNameStartingWith
                            (LocalDateTime.now().minusMonths(2), LocalDateTime.now().minusMonths(1), "J")
                     .forEach(users -> System.out.println(users));
              
              usersRepository.findByUpdatedAtGreaterThanEqualAndNameLike
                     (LocalDateTime.now().minusMonths(1L),"J%")
                     .forEach(users -> System.out.println(users));
       }
       
       @Test
       void quest4() {
              List<Users> usersList = usersRepository.findTop10ByOrderByCreatedAtDesc();
              for (Users user : usersList){
                     System.out.println("ID : " + user.getId() + "  Gender : " + user.getGender() + "  CreatedAt : " + user.getCreatedAt());
              }
              
       }
       
       @Test
       void quest5() {
              usersRepository.findByGenderAndLikeColor(Gender.Male, "Red")
                     .forEach(users -> System.out.println(users.getEmail().substring(0, users.getEmail().indexOf("@"))));
       }
       
       @Test
       void quest6() {
              List<Users> usersList = usersRepository.findAll();
              for (Users user : usersList){
                     if (user.getUpdatedAt().isBefore(user.getCreatedAt())){
                            System.out.println(user);
                     }
              }
       }
       
       @Test
       void quest7() {
              usersRepository.findByEmailContainsAndGenderOrderByCreatedAtDesc("edu", Gender.Female)
                     .forEach(users -> System.out.println(users));
       }
       
       @Test
       void quest8() {
              usersRepository.findAll(Sort.by(Sort.Order.asc("likeColor"), Sort.Order.desc("name")))
                     .forEach(users -> System.out.println(users));
       }
       
       @Test
       void quest9() {
              System.out.println("페이지 = 0, 페이지당 리스트 수 : 10");
              usersRepository.findAll(
                     PageRequest.of(1, 10, Sort.by(Sort.Order.desc("CreatedAt")))
              ).forEach(users -> System.out.println(users));
              
              System.out.println("페이지 = 0, 페이지당 리스트 수 : 10");
              usersRepository.findAll(
                     PageRequest.of(1, 10, Sort.by(Sort.Order.desc("CreatedAt")))
              ). getContent().forEach( users -> System.out.println("content  : " +  users));
       }
       
       @Test
       void quest10() {
              Pageable pageable = PageRequest.of(2, 3);
              usersRepository.findByGenderOrderByIdDesc(Gender.Male, pageable)
                     .forEach(users -> System.out.println(users));
              
              Page<Users> result = usersRepository.findByGenderOrderByIdDesc(Gender.Male,pageable);
              result.getContent().forEach(users -> System.out.println(users));
       }
       
       @Test
       void quest11() {
              //기준일
              LocalDateTime base = LocalDateTime.now().minusMonths(1L);
              //시작일
              LocalDateTime start = LocalDateTime.of(base.getYear(),base.getMonth(),1,0,0,0);
              //마지막일
              LocalDateTime end = base.plusMonths(1L).minusDays(1);
//              LocalDateTime end2 = LocalDateTime.of(end.getYear(), end.getMonth(),end.getDayOfMonth(),0,0,0);
              
              usersRepository.findByCreatedAtBetween(start,
                            end)
                     .forEach(users -> System.out.println(users));
              
       }
}