package hello.hellospring;
//ctrl + alt + <- : 파일이동

import hello.hellospring.domain.Member;
import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration//@Bean생성을 위한,,
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean//스프링 컨테이너에 올려준다.
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();    //구현체
        return new JdbcMemberRepository(dataSource);    //구현체 변경
    }
}
