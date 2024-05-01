package lkm.starterproject.auth.repository;

import lkm.starterproject.auth.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MemberRepository extends JpaRepository<Member, Long>, QuerydslPredicateExecutor<Member> {

    Boolean existsByEmail(String email);

    Member findByEmail(String email);
}
