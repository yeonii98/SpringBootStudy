package hello.hellospring;
//ctrl + alt + <- : 파일이동

import hello.hellospring.domain.Member;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration//@Bean생성을 위한,,
public class SpringConfig {

    private final MemberRepository memberRepository;

    private SpringConfig(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

//    private EntityManager em;
//    private DataSource dataSource;

//    @Autowired
//    public SpringConfig(EntityManager em){
//        this.em = em;
//    }

//    @Autowired
//    public SpringConfig(DataSource dataSource){
//        this.dataSource = dataSource;
//    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean//스프링 컨테이너에 올려준다.
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();    //구현체
//        return new JdbcMemberRepository(dataSource);    //구현체 변경
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);

//    }
}
