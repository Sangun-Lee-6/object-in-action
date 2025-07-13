package com.example.spring_playground.domain;

import com.example.spring_playground.repository.MemberRepository;
import jakarta.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    protected Member() {
        // JPA 기본 생성자
    }

    public Member(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("회원 이름은 필수입니다.");
        }
        this.name = name;
    }

    // 💡 Member의 책임 수행 : 회원가입
    public void join(MemberRepository repository) {
        validateDuplicate(repository);
        repository.save(this);
    }

    private void validateDuplicate(MemberRepository repository) {
        repository.findByName(this.name)
                .ifPresent(m->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public Long getId() { return id; }
    public String getName() { return name; }
}


