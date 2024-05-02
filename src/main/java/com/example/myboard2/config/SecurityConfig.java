package com.example.myboard2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
       
       @Bean
       public BCryptPasswordEncoder bCryptPasswordEncoder(){
              return new  BCryptPasswordEncoder();
       }
       @Bean
       public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
              http
                     .authorizeHttpRequests(
                            request -> request.requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                                   .requestMatchers("/user/**").permitAll() //-> user/login.html  , user/logout.html ,, open 할 것들을 적기
//                                   .requestMatchers("/**").permitAll())
                                   .anyRequest().authenticated()) // 그 이외에 것들은 잠근다.(인증받아야 접근가능하도록)
                            
                     .formLogin(
                            form -> form.loginPage("/user/login")
                                   .loginProcessingUrl("/login")
//                                   .usernameParameter("email") // -> 어느것으로 로그인 할지. -> 이메일로 로그인 시도 하겠다.
                                   .defaultSuccessUrl("/",true))
                     .logout(
                            out -> out.logoutSuccessUrl("/")
                                   .logoutUrl("/logout")
                     ).csrf(csrf->csrf.disable());
              
              return http.build();
       }
}
