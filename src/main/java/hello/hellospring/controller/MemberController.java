package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//@Component 애노테이션이 있으면 스프링 빈으로 자동 등록된다.
@Controller     //스프링 컨테이너에 MemberController를 생성해서 넣어둔다.
public class MemberController {

    private final MemberService memberService;

    @Autowired//MemberController가 생성이 될 때, 스프링 빈에 등록 되어 있는 MemberService를 넣어준다. 빈이란? Spring IoC 컨테이너가 관리하는 자바 객체
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }
}
