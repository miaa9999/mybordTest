package com.example.myboard2.service;

import com.example.myboard2.config.PrincipalDetails;
import com.example.myboard2.entity.UserAccount;
import com.example.myboard2.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {
       @Autowired
       UserAccountRepository userAccountRepository;
       @Override
       public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

              Optional<UserAccount> account = userAccountRepository.findById(username);
              if (account.isEmpty()){
                     throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
              }
              UserAccount userAccount = account.get();
              return new PrincipalDetails(userAccount);
       }
}
