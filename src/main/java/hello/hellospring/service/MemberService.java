package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service//스프링컨테이너에 멤버 서비스 등록
public class MemberService {
    //컨트롤 + shift + T : Test클래스 자동 생성
    //서비스는 비즈니스에 의존적으로 설계 한다.
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }   //직접 new해서 인스턴스 생성이 아닌 외부에서 가져오도록, 따로 new하면 다른 인스턴스가 생성된다
        //이렇게 하면 같은 레파지토리를 쓸 수 있다.
        //memberRepository를 외부에서 넣어준다. => Dependency Injection(DI)

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        validateDuplicatedMember(member);//중복회원 검증, 컨트롤+alt+T : 메소드 생성
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicatedMember(Member member) {
        memberRepository.findByName(member.getName())//컨트롤+alt+V : optional
            .ifPresent(m ->{
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });//m으로 스트림의 요소를 받는다.
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }



}
