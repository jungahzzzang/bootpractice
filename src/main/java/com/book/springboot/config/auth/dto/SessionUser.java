package com.book.springboot.config.auth.dto;

import com.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    /*
    세션user는 인증된 사용자 정보만 필요로 하므로 name, email, picture만 필드로 선언함.
     */

    private String name;
    private String email;
    private String picture;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
