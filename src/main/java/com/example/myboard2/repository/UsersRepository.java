package com.example.myboard2.repository;

import com.example.myboard2.entity.Users;
import com.example.myboard2.vo.Gender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

//@Repository
public interface UsersRepository extends JpaRepository <Users,Long> {
       
       //이름찾기
       List<Users> findByName(String name);
       
       //pink 색상 데이터 중 상위 3개 데이터만 가져오기
       List<Users> findTop3ByLikeColor (String color);
       
       //gender와 color 로 테이블 검색
       List<Users> findByGenderAndLikeColor(Gender gender, String color);
       
       //범위로 검색(After,Before) -> 날짜/시간 데이터에 한정
       List<Users> findByCreatedAtAfter(LocalDateTime searchDate);
       
       //범위로 검색하기(7일전 - 당일)
       List<Users> findByCreatedAtGreaterThanEqual(LocalDateTime searchDate);
       
       //Between으로 자료 검색
       List<Users> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
       List<Users> findByIdBetween(Long startId, Long endId);
       
       //Null or IsNotNull
       List<Users> findByUpdatedAtIsNotNull();
       
       //in 구문(많이 사용)
       List<Users> findByLikeColorIn(List<String> color);
       
       //문자열 관련 쿼리
       List<Users> findByNameStartingWith(String name);
       List<Users> findByNameEndingWith(String name);
       List<Users> findByNameContains(String name);
       List<Users> findByNameLike(String name);
       
       //sorting 하고 순서대로 데이터 추출(order by asc , order by desc)
       List<Users> findByIdBetweenOrderByNameDesc(Long startId, Long endId);
       
       //sort 별도 처리하는 법
       // orange 색상 검색해서 gender 오름차순 createdat 내림차순
       List<Users> findByLikeColor(String color, Sort sort);
       
       //페이징처리
       //주어진 아이디보다 큰 데이터를 내림차순으로 검색한 후 페이징 처리
       // ( id = 200 보다 큰 아이디 , 5번째 페이지, 한페이지당 10개씩)
       Page<Users> findByIdGreaterThanEqualOrderByIdDesc(Long id, Pageable paging);
       
       //quest1
       List<Users> findByNameLikeOrNameLike(String str1 , String str2);
       
       List<Users> findByNameLikeAndGenderOrNameLikeAndGender(String str1 , Gender gender1 , String str2, Gender gender2);
       //quest2
       List<Users> findByEmailContains(String email);
       
       //quest3
       List<Users> findByUpdatedAtBetweenAndNameStartingWith(LocalDateTime startDate, LocalDateTime endDate,String name);
       
       List<Users> findByUpdatedAtGreaterThanEqualAndNameLike(LocalDateTime date, String name);
       
       //quest4
       List<Users> findTop10ByOrderByCreatedAtDesc();

       // quest5 : findByGenderAndLikeColor
       //quset6 : 전체 가져오기 findAll
       //quest7
       List<Users> findByEmailContainsAndGenderOrderByCreatedAtDesc(String email, Gender gender);
       
       //quest8
       List<Users> findAll(Sort sort);
       
       //quest9 findAll
       //quest10
       Page<Users> findByGenderOrderByIdDesc(Gender gender, Pageable paging);
       // quest 11  findByCreatedAtBetween
}
