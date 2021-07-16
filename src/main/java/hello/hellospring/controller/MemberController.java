package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//@Component 애노테이션이 있으면 스프링 빈으로 자동 등록된다.
@Controller     //스프링 컨테이너에 MemberController를 생성해서 넣어둔다.
public class MemberController {

    private final MemberService memberService;

    //생성자 주입 권장
    @Autowired//MemberController가 생성이 될 때, 스프링 빈에 등록 되어 있는 MemberService를 넣어준다. 빈이란? Spring IoC 컨테이너가 관리하는 자바 객체, 스프링이 관리하는 객체에서만 동작
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(MemberForm form){//input의 name과 MemberForm의 자바빈 프로퍼티 이름이 같아야 한다. setXxxx
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member.getName() = " + member.getName());

        memberService.join(member);

        return "redirect:/";
    }
//ctrl + E : 가장 최근에 본 목록
    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
