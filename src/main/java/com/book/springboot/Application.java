package com.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * *@SpringBootApplication
 * 스프링부트의 자동 설정, 스프링 Bean읽기와 생성이 모두 자동으로 설정됨.
 * 이 어노테이션이 있는 위치부터 설정을 읽어가기 때문에 항상 프로젝트 최상단에 위치해야 함.
 */
//@EnableJpaAuditing  //JPA Autiting 활성화
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
