package com.example.myboard2.dto;

import com.example.myboard2.vo.UserRole;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateForm {
       
       @Size(min = 3, max = 15)
       @NotEmpty(message = "사용자 아이디는 필수 입력칸 입니다.")
       private String username;
       
       
       @NotEmpty(message = "비밀번호는 필수 입력칸 입니다.")
       private String password1;
       
       @NotEmpty(message = "비밀번호 확인은 필수입니다.")
       private String password2;
       
       @NotEmpty(message = "이메일은 필수 입력칸입니다.")
       private String email;
       
       private String nickname;
       
       private UserRole userRole;
       
//       public UserAccountDto(Strin userId, String userPassword1, String email, String nickname, UserRole userRole) {
//              this.userId = userId;
//              this.password1 = userPassword1;
//              this.email = email;
//              this.nickname = nickname;
//              this.userRole = userRole;
//       }
//
//       private UserAccountDto userAccountDtoFromEntity (UserAccount userAccount){
//       return new UserAccountDto(
//              userAccount.getUserId(),
//              userAccount.getUserPassword(),
//              userAccount.getEmail(),
//              userAccount.getNickname(),
//              userAccount.getUserRole()
//       );
//       }
}
