package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {//인터페이스가 인터페이스를 상속할 때 : extends

    @Override
    Optional<Member> findByName(String name);
}
