package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional  //테스트가 끝나면 항상 rollback(@Test일 때만), 롤백은 데이터베이스에서 업데이트에 오류가 발생할 때, 이전 상태로 되돌리는 것을 말한다.
class MemberServiceIntegrationTest {
    //shift+alt+f10 : 이전 실행
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


//    @AfterEach//메서드가 끝날 때 마다 동작하는 것, 콜백 메서드,
//    public void afterEach(){
//        memberRepository.clearStore();//테스트가 실행 될 때마다 한 번씩 지우는 과정
//    }

    @Test
    void 회원가입() {//테스트는 한글로 적어도 상관 없음.
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        try{
//            memberService.join(member2);
//           // fail();   //강제 실패
//        } catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}