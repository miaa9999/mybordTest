package com.example.myboard2.entity;

import com.example.myboard2.vo.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount {
       
       @Id
       @Column(length = 50, nullable = false)
       private String username;
       
       @Column(nullable = false)
       private String userPassword;
       
       @Column(length = 100)
       private String email;
       
       @Column(length = 15)
       private String nickname;
       
       @Enumerated(EnumType.STRING)
       private UserRole userRole;
}
