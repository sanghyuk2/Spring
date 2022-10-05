package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//JpaRepository<>를 extends 하면 SpringDataJpa가 인터페이스에 구현체를 만들고 스프링 빈에 등록한다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    //name이란게 일관성을 가진 것이 아니기 때문에 아래 메소드를 만들어주면 메소드의 이름 Name을 보고 아래 query가 실행된다.
    //JPQL select m from Member m where m.name = ?
    //스프링데이터Jpa가 부리는 마법 : 인터페이스 이름만으로 개발이 끝난다.
    @Override
    Optional<Member> findByName(String name);
}
