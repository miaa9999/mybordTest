package com.example.myboard2.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class PaginationService {
       private static final int barLength = 5;
       public List<Integer> getPaginationBarNum(int currentPageNum, int totalPages){
              int startNum = Math.max(currentPageNum-(barLength/2),0);
              int endNum = Math.min(startNum + barLength, totalPages);
              
              return IntStream.range(startNum,endNum).boxed().toList();
              // boxed -> 리스트의 길이를 초과하는 것은 마지막에서부터 빼는것.
       }
}
