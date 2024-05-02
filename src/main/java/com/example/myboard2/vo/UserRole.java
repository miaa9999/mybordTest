package com.example.myboard2.vo;

import lombok.Getter;

import java.net.URI;

@Getter
public enum UserRole {
       ADMIN("ROLE ADMIN"),
       USER("ROLE USER");
       
       private String value;
       UserRole(String value){
              this.value = value;
       }
}
