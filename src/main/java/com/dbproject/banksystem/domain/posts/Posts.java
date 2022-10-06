package com.dbproject.banksystem.domain.posts;

import com.dbproject.banksystem.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
//import org.dom4j.swing.XMLTableColumnDefinition;
//import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

// Lombok 은 코드를 단순화시켜주지만 필수 어노테이션은 아님
// 주요 어노테이션 @Entity 를 가깝게 둠
// 실제 Posts 클래스는 DB 테이블과 매칭될 클래스 -> Entity 클래스
// JPA 사용 시 Entity 클래스 수정을 통해 작업

// @Entity
// * 테이블과 링크 될 클래스임을 나타냄
// * ex) SalesManager.java -> sales_manager table
// 웬만하면 Entity PK는 Long 타입 Auto_increment 추천

// @NoArgsConstructor
// * 기본 생성자 자동 추가
// * public Posts() {}와 같은 효과

// Entity 클래스에서는 절대 Setter 메소드를 만들지 않음
// Setter 없이 어떻게 값을 채워 DB에 INSERT?
// 생성자를 통해 최종 값 채운 후 DB에 INSERT

@Getter
@NoArgsConstructor
@Entity // JPA 어노테이션
public class Posts extends BaseTimeEntity {

    // @GeneratedValue
    // * PK 생성 규칙을 나타냄
    // * 2.0 버전에서는 GenerationType.IDENTITY 옵션 추가해야 auto_increment 적용

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column
    // * 테이블의 칼럼을 나타냄
    // * 굳이 선언 안해도 해당 클래스의 필드는 모두 칼럼
    // * 문자열의 경우 VARCHAR(255) 기본값

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    // @Builder
    // * 해당 클래스의 빌더 패턴 클래스 생성
    // * 생성자 상단 선언 시 생성자에 포함된 필드만 빌더에 포함
    // * 빌더를 사용하게 되면 어느 필드에 어떤 값을 채워야 할지 명확해짐

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}