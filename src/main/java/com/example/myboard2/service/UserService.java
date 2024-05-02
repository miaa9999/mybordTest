package com.example.myboard2.service;

import com.example.myboard2.dto.UserCreateForm;
import com.example.myboard2.entity.UserAccount;
import com.example.myboard2.repository.UserAccountRepository;
import com.example.myboard2.vo.UserRole;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
       
       private final UserAccountRepository userAccountRepository;
       
       public UserService(UserAccountRepository userAccountRepository) {
              this.userAccountRepository = userAccountRepository;
       }
       
       @Autowired
       PasswordEncoder passwordEncoder;
       
       @Autowired
       EntityManager em;
       @Transactional
       public void createUser(UserCreateForm userCreateForm) {
              UserAccount account = new UserAccount();
              account.setUsername(userCreateForm.getUsername());
              account.setUserPassword(passwordEncoder.encode(userCreateForm.getPassword1()));
              account.setEmail(userCreateForm.getEmail());
              account.setNickname(userCreateForm.getNickname());
              if ("ADMIN".equals(userCreateForm.getUsername().toUpperCase())){
                     account.setUserRole(UserRole.ADMIN);
              }else {
                     account.setUserRole(UserRole.USER);
              }
              em.persist(account);
       }
       
       
}
