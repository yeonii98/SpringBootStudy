package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

//테스트를 먼저 만들고 구현 클래스를 만들어서 실행 해보는 것 - TDD
public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach//메서드가 끝날 때 마다 동작하는 것, 콜백 메서드,
    public void afterEach() {
        repository.clearStore();//테스트가 실행 될 때마다 한 번씩 지우는 과정
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);//(1,"spring")

        Member result = repository.findById(member.getId()).get();
//        System.out.println("result = " + (result == member)); //result = true
//        Assertions.assertEquals(reuslt , member);
        assertThat(member).isEqualTo(result);
    }

    @Test//옆에 테스트 버튼이 생김, 컨트롤+쉬프트+F10 : 테스트 실행
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();//복붙 컨트롤+D
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }
}
