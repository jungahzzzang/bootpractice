package com.book.springboot.config.auth;

import com.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity  //스프링 시큐리티 설정 활성화
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().disable() //h2-console 화면 사용 위해 해당 옵션들을 disable하겠다.
                .and()
                .authorizeRequests()   //URL별 권한 관리를 설정하는 옵션의 시작점. 선언되어야만 antMatchers 옵션 사용 가능
                //권한 관리 대상을 지정하는 옵션
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                //설정된 값 이외 나머지 URL들을 나타냄.
                //나머지 URL들은 모두 인증된 사용자들에게만 허용
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/")  //로그아웃 기능에 대한 여러 설정의 진입점
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);

    }
}
