package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    //Map<Key, Value>
    private static Map<Long, Member> store = new HashMap<>();
    //Key 값 생성.
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);

        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        //열거 형의 모든 상수는 해당 형식의 암시적인 public static T [] values() 메서드를 호출하여 얻을 수 있습니다.
        //reference) https://docs.oracle.com/javase/8/docs/api/java/lang/Enum.html
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
