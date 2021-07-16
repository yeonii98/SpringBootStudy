package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    //레파지토리는 개발자스러운 용어
    Member save(Member member);//회원 저장
    Optional<Member> findById(Long id);//null 방지, 존재할 수도 있지만 안 할 수도 있는 객체
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
