package com.book.springboot.domain.posts;   //게시글,댓글,회원 등 소프트웨어에 대한 요구사항 or 문제 영역인 도메인을 담을 패키지

import com.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity //테이블과 링크될 클래스임을 나타냄.
public class Posts extends BaseTimeEntity {    //실제 DB의 테이블과 매칭될 클래스(Entity 클래스)
                        //JPA를 사용하면 실제 쿼리가 아닌 이 Entity 클래스의 수정을 통해 DB 작업 가능

    @Id //해당 테이블의 PK 필드를 나타냄.
    //PK의 생성 규칙을 나타냄.
    //스프링부트 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야만 auto_increment됨.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //테이블의 컬럼을 나타내며 생략해도 해당 클래스의 필드는 모두 컬럼이 됨.
    //사용하는 이유는 기본값 이외 추가 변경이 필요한 경우에 사용.
    //ex)문자열의 기본값은 varchar(255)dlsep 500으로 늘리고 싶을 때
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    //게시글 수정
   public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
