package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//controller를 통해서 외부 요청을 받고 service에서 비즈니스 로직을 만들고 repository에서 데이터를 저장
//@Repository//구현체
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    
    @Override
    public Member save(Member member) {
        member.setId(++sequence);           //저장 할 때 마다 id값을 ++
        store.put(member.getId(),member);   //id와 memeber 저장
        return null;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))//member의 이름이 name인 데이터가 가져옴, member로 스트림의 요소를 받는다.
                .findAny();//찾아지는 객체를 순서에 상관없이 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }//store의 value들을 리턴해준다.

    public void clearStore(){
        store.clear();
    }
}
