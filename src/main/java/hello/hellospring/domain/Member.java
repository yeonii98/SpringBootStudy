package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity//jpa가 관리하는 엔티티, Member 클래스가 MySQL에 테이블이 자동으로 생성된다.
public class Member {

    @Id//PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)//자동 생성
    private Long id;    //고유번호


    private String name;//회원이름

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
