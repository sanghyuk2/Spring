package hello.hellospring.domain;

import javax.persistence.*;

@Entity
public class Member {

    //db에 query 넣으면 id를 자동생성해주는것을 identity라고 한다.
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
