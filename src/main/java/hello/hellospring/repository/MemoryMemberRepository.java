package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

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
                .filter(member -> member.getName().equals(name))//member의 이름이 name인 데이터가 가져옴
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
